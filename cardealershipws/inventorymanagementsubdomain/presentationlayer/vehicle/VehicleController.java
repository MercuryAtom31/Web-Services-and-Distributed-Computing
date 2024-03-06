package com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.vehicle;

import com.benzair.cardealershipws.inventorymanagementsubdomain.businesslayer.vehicle.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/inventories/{inventoryId}/vehicles")
public class VehicleController {

    VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping()
    public ResponseEntity<List<VehicleResponseModel>> getVehicles(@PathVariable String inventoryId, @RequestParam(required = false) Map<String, String> queryParams){
        return ResponseEntity.status(HttpStatus.FOUND).body(vehicleService.getVehicles(inventoryId, queryParams));
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseModel> getVehicleByVehicleId(@PathVariable String vehicleId, @PathVariable String inventoryId){
        return ResponseEntity.status(HttpStatus.FOUND).body(vehicleService.getVehicleByVehicleId(vehicleId));
    }

    @PostMapping()
    public ResponseEntity<VehicleResponseModel> createVehicle(@RequestBody VehicleRequestModel contactRequestModel, @PathVariable String inventoryId){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.addVehicle(contactRequestModel));
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseModel> updateVehicle(@RequestBody VehicleRequestModel vehicleRequestModel,
                                                              @PathVariable String vehicleId, @PathVariable String inventoryId){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.updateVehicle(vehicleId, vehicleRequestModel));
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseModel> deleteVehicle(@PathVariable String vehicleId, @PathVariable String inventoryId){
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
