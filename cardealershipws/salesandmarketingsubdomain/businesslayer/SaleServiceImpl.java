package com.benzair.cardealershipws.salesandmarketingsubdomain.businesslayer;

import com.benzair.cardealershipws.customerrelationssubdomain.dataaccesslayer.Customer;
import com.benzair.cardealershipws.customerrelationssubdomain.dataaccesslayer.CustomerRepository;
import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee.Employee;
import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee.EmployeeRepository;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.inventory.Inventory;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.inventory.InventoryRepository;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Status;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Vehicle;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.VehicleRepository;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.Price;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.Sale;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.SaleIdentifier;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.SaleRepository;
import com.benzair.cardealershipws.salesandmarketingsubdomain.mapperlayer.SaleRequestMapper;
import com.benzair.cardealershipws.salesandmarketingsubdomain.mapperlayer.SaleResponseMapper;
import com.benzair.cardealershipws.salesandmarketingsubdomain.presentationlayer.SaleRequestModel;
import com.benzair.cardealershipws.salesandmarketingsubdomain.presentationlayer.SaleResponseModel;
import com.benzair.cardealershipws.utils.exceptions.InvalidInputException;
import com.benzair.cardealershipws.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //Tells the Spring container, I want you to manage this class. Meaning, manage its lifecycle. Inject it, etc.
@Slf4j // This will allow us to add debug statements.
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final VehicleRepository vehicleRepository;

    private final InventoryRepository inventoryRepository;
    private final SaleResponseMapper saleResponseMapper;
    private final SaleRequestModel saleRequestModel;

    private final SaleRequestMapper saleRequestMapper;

    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, VehicleRepository vehicleRepository, InventoryRepository inventoryRepository, SaleResponseMapper saleResponseMapper, SaleRequestModel saleRequestModel, SaleRequestMapper saleRequestMapper) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.vehicleRepository = vehicleRepository;
        this.inventoryRepository = inventoryRepository;
        this.saleResponseMapper = saleResponseMapper;
        this.saleRequestModel = saleRequestModel;
        this.saleRequestMapper = saleRequestMapper;
    }


    @Override
    public SaleResponseModel getCustomerPurchaseBySaleId(String customerId, String saleId) {

        //Verify customer exists.
        //q: what is the algorithm to get a customer by id?
        Customer customer = customerRepository.findByCustomerIdentifier_CustomerId(customerId);

        if(customer == null){
            throw new InvalidInputException("CustomerId provided is invalid" + customerId);
        }

        //Verify sale exists.
        Sale sale = saleRepository.findSaleByCustomerIdentifier_CustomerIdAndSaleIdentifier_SaleId(customerId, saleId);

        if(sale == null){
            throw new NotFoundException("Unknown SaleId provided: " + saleId);
        }
        // employeeRepository.findEmployeeByEmployeeIdentifier_EmployeeId
        Employee employee = employeeRepository.findEmployeeByEmployeeIdentifier(sale.getEmployeeIdentifier().getEmployeeId());

        if (employee == null) {
            throw new InvalidInputException("EmployeeId provided is invalid: " + sale.getEmployeeIdentifier().getEmployeeId());

        }
        // vehicleRepository.findVehicleByVehicleIdentifier_Vin
        Vehicle vehicle = vehicleRepository.findVehicleByVehicleIdentifier_VehicleId(sale.getVehicleIdentifier().getVehicleId());
        if (vehicle == null){
            throw new InvalidInputException("Vin provided is invalid: " + sale.getVehicleIdentifier().getVehicleId());
        }
        return saleResponseMapper.entityToResponseModel(sale, customer, employee, vehicle);
    }

    //CLass code with errors.

//    @Override
//    public List<SaleResponseModel> getAllPurchasesForCustomer(String customerId) {
//
//        Customer customer = customerRepository.findByCustomerIdentifier_CustomerId(customerId);
//
//        if(customer == null){
//            throw new InvalidInputException("CustomerId provided is invalid" + customerId);
//        }
//        List<SaleResponseModel> saleResponseModelList = new ArrayList<>();
//        List<Sale> saleList = saleRepository.findSalesByCustomerIdentifier_CustomerId(customerId);
//
//        saleList.forEach(purchase -> {
//            Employee employee = employeeRepository.findEmployeeByEmployeeIdentifier(purchase.getEmployeeIdentifier().getEmployeeId());
//            if (employee == null){
//                throw new InvalidInputException("EmployeeId provided is invalid: " + purchase.getEmployeeIdentifier().getEmployeeId());
//            }
//            Vehicle vehicle = vehicleRepository.findVehicleByVehicleIdentifier_VehicleId(purchase.getVehicleIdentifier().getVehicleId());
//            if (vehicle == null){
//                throw new InvalidInputException("Vin provided is invalid: " + purchase.getVehicleIdentifier().getVehicleId());
//            }
//            //return saleResponseMapper.entityToResponseModel(purchase, customer, employee, vehicle);
//            return saleResponseMapper.entityToResponseModel(purchase, customer, employee, vehicle);
//
//        });
//
//        return saleResponseModelList;
//    }

    //GPT code:

    /*
     This method retrieves information about a customer's purchases, performs various validations,
     and then converts the data into SaleResponseModel objects, which are added to a list and returned.
     If any validation fails during the process, an InvalidInputException is thrown with an appropriate error message.
     */
@Override
public List<SaleResponseModel> getAllPurchasesForCustomer(String customerId) {

    Customer customer = customerRepository.findByCustomerIdentifier_CustomerId(customerId);

    if(customer == null){
        throw new InvalidInputException("CustomerId provided is invalid" + customerId);
    }

    List<SaleResponseModel> saleResponseModelList = new ArrayList<>();
    List<Sale> saleList = saleRepository.findSalesByCustomerIdentifier_CustomerId(customerId);

    saleList.forEach(purchase -> {

        log.debug("Purchase: " + purchase.toString());

        Employee employee = employeeRepository.findEmployeeByEmployeeIdentifier(purchase.getEmployeeIdentifier().getEmployeeId());
        if (employee == null){
            throw new InvalidInputException("EmployeeId provided is invalid: " + purchase.getEmployeeIdentifier().getEmployeeId());
        }
        Vehicle vehicle = vehicleRepository.findVehicleByVehicleIdentifier_VehicleId(purchase.getVehicleIdentifier().getVehicleId());
        if (vehicle == null){
            throw new InvalidInputException("Vin provided is invalid: " + purchase.getVehicleIdentifier().getVehicleId());
        }

        SaleResponseModel saleResponseModel = saleResponseMapper.entityToResponseModel(purchase, customer, employee, vehicle);
        saleResponseModelList.add(saleResponseModel);
    });

    return saleResponseModelList;
}

    @Override
    public SaleResponseModel makeCustomerPurchase(SaleRequestModel saleRequestModel, String customerId){

    //Verify customer exists.
        Customer customer = customerRepository.findByCustomerIdentifier_CustomerId(customerId);

        if(customer == null){
            throw new InvalidInputException("CustomerId provided is invalid" + customerId);
        }

        //Verify employee exists.
        Employee employee = employeeRepository.findEmployeeByEmployeeIdentifier(saleRequestModel.getEmployeeId());
        if (employee == null){
            throw new InvalidInputException("Inventory provided is invalid: " + saleRequestModel.getInventoryId());
        }

        //Verify inventory exists.
//        Inventory inventory = inventoryRepository.findByInventoryIdentifier_InventoryId(saleRequestModel.getInventoryId());
        Inventory inventory = inventoryRepository.findInventoryByInventoryIdentifier_InventoryId(saleRequestModel.getInventoryId());
        if (inventory == null){
            throw new InvalidInputException("EmployeeId provided is invalid: " + saleRequestModel.getInventoryId());
        }

        //Verify that vehicle exists in inventory.
//        Vehicle vehicle = vehicleRepository.findByInventoryIdentifier_InventoryAndVehicleIdentifier_Vin(inventory.getInventoryIdentifier().
//                getInventoryId(), saleRequestModel.getVin());
        Vehicle vehicle = vehicleRepository.findByInventoryIdentifier_InventoryIdAndVehicleIdentifier_Vin(inventory.getInventoryIdentifier().
                getInventoryId(), saleRequestModel.getVin());
        if (vehicle == null){
            throw new InvalidInputException("VIN provided is invalid: " + saleRequestModel.getVin());
        }

        //Verify that vehicle is not already sold.
        if (vehicle.getStatus() != Status.Available){
            throw new InvalidInputException("Vehicle is not available for sale: " + saleRequestModel.getVin());
        }

        //Convert request model to entity.
        Sale sale = saleRequestMapper.requestModelToEntity(saleRequestModel, new SaleIdentifier(),
                inventory.getInventoryIdentifier(), vehicle.getVehicleIdentifier(),
                employee.getEmployeeIdentifier(),customer.getCustomerIdentifier(),
                new Price((saleRequestModel.getSalePrice()), saleRequestModel.getCurrency()));

        Sale savedSale = saleRepository.save(sale);

        //Convert savedSale entity to a sale response model.
        return saleResponseMapper.entityToResponseModel(savedSale, customer, employee, vehicle);
}

}
