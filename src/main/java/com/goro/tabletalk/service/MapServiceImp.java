package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.MapDTO;
import com.goro.tabletalk.entity.MapEntity;
import com.goro.tabletalk.entity.TableMapEntity;
import com.goro.tabletalk.mapper.MapMapper;
import com.goro.tabletalk.repository.MapRepository;
import com.goro.tabletalk.repository.TableMapRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MapServiceImp implements MapService {
    @Autowired
    MapMapper mapMapper;
    @Autowired
    private MapRepository mapRepository;
    @Autowired
    private TableMapRepository tableMapRepository;

    @Override
    public MapDTO createMap(String name) {
        MapEntity map = new MapEntity();
        map.setName(name);
        return mapMapper.toMapDTO(mapRepository.save(map));
    }

    @Override
    public List<MapDTO> getAllMaps() {
        return mapMapper.toMapDTO(mapRepository.findAll());
    }

    @Override
    public MapDTO getMap(Long id) {
        return mapMapper.toMapDTO(mapRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Map with id " + id + " not found")));
    }

    @Override
    public MapDTO updateMap(MapDTO map) {
        MapEntity mapReceived = mapMapper.toMapEntity(map);
        MapEntity mapEntity = mapRepository.findById(map.getId())
                .orElse(null);
        if (mapEntity == null) {
            mapEntity = new MapEntity();
            mapEntity.setName(mapReceived.getName());
            mapRepository.save(mapEntity);
        }

        List<TableMapEntity> existingTableMaps = mapEntity.getTableMap();
        List<TableMapEntity> receivedTableMaps = mapReceived.getTableMap();

        Set<Long> existingTableIds = existingTableMaps.stream()
                .map(tableMapEntity -> tableMapEntity.getTable().getId())
                .collect(Collectors.toSet());

        Set<Long> recievedTableIds = receivedTableMaps.stream()
                .map(tableMapEntity -> tableMapEntity.getTable().getId())
                .collect(Collectors.toSet());

        List<TableMapEntity> updatedTables = new ArrayList<>();
        for (TableMapEntity existingTable : existingTableMaps) {
            if (!recievedTableIds.contains(existingTable.getTable().getId())) {
                tableMapRepository.deleteById(existingTable.getId());
            }
        }
        for (TableMapEntity receivedTable : receivedTableMaps) {
            if (receivedTable.getTable().getId() == null || !existingTableIds.contains(receivedTable.getTable().getId())) {
                receivedTable.setMap(mapEntity);
                updatedTables.add(receivedTable);
            } else {
                TableMapEntity tableMapEntity = tableMapRepository.findById(receivedTable.getId())
                        .orElseThrow(() -> new EntityNotFoundException("TableMap with id " + receivedTable.getId() + " not found"));

                tableMapEntity.setAngle(receivedTable.getAngle());
                tableMapEntity.setColor(receivedTable.getColor());
                tableMapEntity.setGapX(receivedTable.getGapX());
                tableMapEntity.setGapY(receivedTable.getGapY());
                tableMapEntity.setShape(receivedTable.getShape());
                tableMapEntity.setHeight(receivedTable.getHeight());
                tableMapEntity.setWidth(receivedTable.getWidth());
                tableMapEntity.setX(receivedTable.getX());
                tableMapEntity.setY(receivedTable.getY());
                tableMapEntity.setZ(receivedTable.getZ());

                updatedTables.add(tableMapEntity);
            }
        }

        mapEntity.setTableMap(tableMapRepository.saveAll(updatedTables));
        mapEntity.setName(mapReceived.getName());

        return mapMapper.toMapDTO(mapRepository.save(mapEntity));
    }


    @Override
    public void deleteMap(Long id) {
        MapEntity mapEntity = mapRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Map with id " + id + " not found"));

        tableMapRepository.deleteAll(mapEntity.getTableMap());
        mapEntity.setTableMap(null);
        mapRepository.deleteById(id);
    }
}
