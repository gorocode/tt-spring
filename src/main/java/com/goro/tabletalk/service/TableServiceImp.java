package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.TableDTO;
import com.goro.tabletalk.entity.TableEntity;
import com.goro.tabletalk.entity.TableMapEntity;
import com.goro.tabletalk.mapper.TableMapper;
import com.goro.tabletalk.repository.TableMapRepository;
import com.goro.tabletalk.repository.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TableServiceImp implements TableService {
    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private TableMapRepository tableMapRepository;

    @Override
    public TableDTO saveTable(TableDTO table) {
        if (tableRepository.findByNumberAndLocation(table.getNumber(), table.getLocation()).isPresent()) {
            throw new NonUniqueResultException("Table with number " + table.getNumber() +
                    " and location " + table.getLocation().getTableLocation() +
                    " already exists");
        }
        return tableMapper.toTableDTO(
                tableRepository.save(tableMapper.toTableEntity(table)));
    }

    @Override
    public TableDTO updateTable(TableDTO table) {
        tableRepository.findById(table.getId()).orElseThrow(
                () -> new EntityNotFoundException("Table with id " + table.getId() + " not found."));
        Optional<TableEntity> tableFound = tableRepository.findByNumberAndLocation(table.getNumber(),
                table.getLocation());
        if (tableFound.isPresent() && !Objects.equals(tableFound.get().getId(), table.getId())) {
            throw new NonUniqueResultException("Table with number " + table.getNumber() +
                    " and location " + table.getLocation().getTableLocation() +
                    " already exists");
        }
        return tableMapper.toTableDTO(tableRepository.save(tableMapper.toTableEntity(table)));
    }

    @Override
    public List<TableDTO> findAllTables() {
        return tableMapper.toTableDTO(
                tableRepository.findByDeletedFalse());
    }

    @Override
    public void deleteTableById(Long id) {
        TableEntity tableEntity = tableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Table with id " + id + " not found"));
        List<TableMapEntity> tableMaps = tableEntity.getTableMaps();
        tableEntity.setTableMaps(null);

        tableMapRepository.deleteAll(tableMaps);
        
        if (tableEntity.getOrders() != null && !tableEntity.getOrders().isEmpty()) {
            tableEntity.setDeleted(true);
            tableRepository.save(tableEntity);
        } else {
            tableRepository.delete(tableEntity);
        }
    }
}
