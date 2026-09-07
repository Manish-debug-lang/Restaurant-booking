package com.api.restaurantbooking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.api.restaurantbooking.entity.Booking;
import com.api.restaurantbooking.entity.Restaurant;
import com.api.restaurantbooking.entity.RestaurantTable;
import com.api.restaurantbooking.repository.BookingRepository;
import com.api.restaurantbooking.repository.MenuItemRepository;
import com.api.restaurantbooking.repository.RestaurantPhotoRepository;
import com.api.restaurantbooking.repository.RestaurantRepository;
import com.api.restaurantbooking.repository.RestaurantReviewRepository;
import com.api.restaurantbooking.repository.RestaurantTableRepository;
import com.api.restaurantbooking.entity.MenuItem;
import com.api.restaurantbooking.entity.RestaurantPhoto;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class AdminController {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantTableRepository restaurantTableRepository;

    private final BookingRepository bookingRepository;

    private final RestaurantReviewRepository restaurantReviewRepository;

    private final RestaurantPhotoRepository restaurantPhotoRepository;

    private final MenuItemRepository menuItemRepository;


    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public AdminController(
            RestaurantRepository restaurantRepository,
            RestaurantTableRepository restaurantTableRepository,
            BookingRepository bookingRepository,
            RestaurantReviewRepository restaurantReviewRepository,
            RestaurantPhotoRepository restaurantPhotoRepository,
            MenuItemRepository menuItemRepository) {

        this.restaurantRepository = restaurantRepository;
        this.restaurantTableRepository = restaurantTableRepository;
        this.bookingRepository = bookingRepository;
        this.restaurantReviewRepository = restaurantReviewRepository;
        this.restaurantPhotoRepository = restaurantPhotoRepository;
        this.menuItemRepository = menuItemRepository;
    }


    // ==========================================
    // ADMIN DASHBOARD
    // ==========================================

    @GetMapping("/admin")
    public String adminDashboard(Model model) {

        long totalRestaurants =
                restaurantRepository.count();

        long totalTables =
                restaurantTableRepository.count();

        long totalBookings =
                bookingRepository.count();

        long totalReviews =
                restaurantReviewRepository.count();


        model.addAttribute(
                "totalRestaurants",
                totalRestaurants
        );

        model.addAttribute(
                "totalTables",
                totalTables
        );

        model.addAttribute(
                "totalBookings",
                totalBookings
        );

        model.addAttribute(
                "totalReviews",
                totalReviews
        );


        return "admin-dashboard";
    }


    // ==========================================
    // ADMIN BOOKINGS
    // ==========================================

    @GetMapping("/admin/bookings")
    public String adminBookings(Model model) {

        List<Booking> bookings =
                bookingRepository.findAll();

        List<Restaurant> restaurants =
                restaurantRepository.findAll();

        List<RestaurantTable> tables =
                restaurantTableRepository.findAll();


        // Restaurant ID → Restaurant Name

        Map<Long, String> restaurantNames =
                new HashMap<>();

        for (Restaurant restaurant : restaurants) {

            restaurantNames.put(
                    restaurant.getId(),
                    restaurant.getName()
            );
        }


        // Table ID → Table Number

        Map<Long, Integer> tableNumbers =
                new HashMap<>();

        for (RestaurantTable table : tables) {

            tableNumbers.put(
                    table.getId(),
                    table.getTableNumber()
            );
        }


        model.addAttribute(
                "bookings",
                bookings
        );

        model.addAttribute(
                "restaurantNames",
                restaurantNames
        );

        model.addAttribute(
                "tableNumbers",
                tableNumbers
        );


        return "admin-bookings";
    }


    // ==========================================
    // CANCEL BOOKING
    // ==========================================

    @PostMapping("/admin/bookings/delete/{id}")
    public String deleteBooking(
            @PathVariable Long id) {

        bookingRepository.deleteById(id);

        return "redirect:/admin/bookings";
    }


    // ==========================================
    // ADMIN RESTAURANTS
    // ==========================================

    @GetMapping("/admin/restaurants")
    public String adminRestaurants(Model model) {

        model.addAttribute(
                "restaurants",
                restaurantRepository.findAll()
        );

        return "admin-restaurants";
    }


    // ==========================================
    // SHOW ADD RESTAURANT FORM
    // ==========================================

    @GetMapping("/admin/restaurants/add")
    public String showAddRestaurantForm(
            Model model) {

        model.addAttribute(
                "restaurant",
                new Restaurant()
        );

        return "admin-add-restaurant";
    }


    // ==========================================
    // SAVE RESTAURANT
    // ==========================================

    @PostMapping("/admin/restaurants/save")
    public String saveRestaurant(
            Restaurant restaurant) {

        restaurantRepository.save(restaurant);

        return "redirect:/admin/restaurants";
    }


 // ==========================================
 // CONFIRM DELETE RESTAURANT
 // ==========================================

 @GetMapping("/admin/restaurants/delete/{id}")
 public String confirmDeleteRestaurant(
         @PathVariable Long id,
         Model model) {

     Restaurant restaurant =
             restaurantRepository.findById(id).orElse(null);

     if (restaurant == null) {
         return "redirect:/admin/restaurants";
     }

     model.addAttribute("restaurant", restaurant);

     return "admin-confirm-delete-restaurant";
 }


 // ==========================================
 // DELETE RESTAURANT
 // ==========================================

 @Transactional
 @PostMapping("/admin/restaurants/delete/{id}")
 public String deleteRestaurant(
         @PathVariable Long id) { 

     // Delete related bookings
     bookingRepository.deleteByRestaurantId(id);

     // Delete related tables
     restaurantTableRepository.deleteByRestaurantId(id);

     // Delete related reviews
     restaurantReviewRepository.deleteByRestaurantId(id);

     // Delete related photos
     restaurantPhotoRepository.deleteByRestaurantId(id);

     // Delete related menu items
     menuItemRepository.deleteByRestaurantId(id);

     // Delete restaurant
     restaurantRepository.deleteById(id);

     return "redirect:/admin/restaurants";
 }
 
 // ==========================================
 // ADMIN TABLES
 // ==========================================

 @GetMapping("/admin/tables")
 public String adminTables(Model model) {

     model.addAttribute(
             "restaurants",
             restaurantRepository.findAll()
     );

     model.addAttribute(
             "tables",
             restaurantTableRepository.findAll()
     );

     return "admin-tables";
 }
 
//==========================================
//SHOW ADD TABLE FORM
//==========================================

@GetMapping("/admin/tables/add")
public String showAddTableForm(Model model) {

  model.addAttribute(
          "table",
          new RestaurantTable()
  );

  model.addAttribute(
          "restaurants",
          restaurantRepository.findAll()
  );

  return "admin-add-table";
}
//==========================================
//SAVE TABLE
//==========================================

@PostMapping("/admin/tables/save")
public String saveTable(RestaurantTable table) {

 // Save table to MySQL
 restaurantTableRepository.save(table);

 // Go back to table list
 return "redirect:/admin/tables";
}

//==========================================
//DELETE TABLE
//==========================================

@GetMapping("/admin/tables/delete/{id}")
public String deleteTable(
     @PathVariable Long id,
     Model model) {

 // Check whether this table has bookings
 boolean hasBookings =
         bookingRepository.existsByTableId(id);

 // ==========================================
 // TABLE HAS BOOKINGS
 // ==========================================

 if (hasBookings) {

     model.addAttribute(
             "error",
             "This table cannot be deleted because it has bookings."
     );

     model.addAttribute(
             "restaurants",
             restaurantRepository.findAll()
     );

     model.addAttribute(
             "tables",
             restaurantTableRepository.findAll()
     );

     return "admin-tables";
 }

 // ==========================================
 // TABLE HAS NO BOOKINGS
 // ==========================================

 restaurantTableRepository.deleteById(id);

 return "redirect:/admin/tables";
}
//==========================================
//ADMIN MENU
//==========================================

@GetMapping("/admin/menu")
public String adminMenu(Model model) {

 model.addAttribute(
         "restaurants",
         restaurantRepository.findAll()
 );

 model.addAttribute(
         "menuItems",
         menuItemRepository.findAll()
 );

 return "admin-menu";
}

//==========================================
//SHOW ADD MENU ITEM FORM
//==========================================

@GetMapping("/admin/menu/add")
public String showAddMenuForm(Model model) {

 model.addAttribute(
         "menuItem",
         new MenuItem()
 );

 model.addAttribute(
         "restaurants",
         restaurantRepository.findAll()
 );

 return "admin-add-menu";
}

//==========================================
//SAVE MENU ITEM
//==========================================

@PostMapping("/admin/menu/save")
public String saveMenuItem(MenuItem menuItem) {

 // Save menu item to MySQL
 menuItemRepository.save(menuItem);

 // Go back to menu list
 return "redirect:/admin/menu";
}

//==========================================
//DELETE MENU ITEM
//==========================================

@GetMapping("/admin/menu/delete/{id}")
public String deleteMenuItem(@PathVariable Long id) {

 menuItemRepository.deleteById(id);

 return "redirect:/admin/menu";
}

//==========================================
//ADMIN PHOTOS
//==========================================

@GetMapping("/admin/photos")
public String adminPhotos(Model model) {

 model.addAttribute(
         "restaurants",
         restaurantRepository.findAll()
 );

 model.addAttribute(
         "photos",
         restaurantPhotoRepository.findAll()
 );

 return "admin-photos";
}

//==========================================
//SHOW ADD PHOTO FORM
//==========================================

@GetMapping("/admin/photos/add")
public String showAddPhotoForm(Model model) {

 model.addAttribute(
         "photo",
         new RestaurantPhoto()
 );

 model.addAttribute(
         "restaurants",
         restaurantRepository.findAll()
 );

 return "admin-add-photo";
}

//==========================================
//SAVE RESTAURANT PHOTO
//==========================================

@PostMapping("/admin/photos/save")
public String savePhoto(RestaurantPhoto photo) {

 // Save photo to MySQL
 restaurantPhotoRepository.save(photo);

 // Go back to photo list
 return "redirect:/admin/photos";
}
//==========================================
//DELETE RESTAURANT PHOTO
//==========================================

@GetMapping("/admin/photos/delete/{id}")
public String deletePhoto(@PathVariable Long id) {

 restaurantPhotoRepository.deleteById(id);

 return "redirect:/admin/photos";
}
//==========================================
//ADMIN REVIEWS
//==========================================

@GetMapping("/admin/reviews")
public String adminReviews(Model model) {

 model.addAttribute(
         "restaurants",
         restaurantRepository.findAll()
 );

 model.addAttribute(
         "reviews",
         restaurantReviewRepository.findAll()
 );

 return "admin-reviews";
}
//==========================================
//DELETE RESTAURANT REVIEW
//==========================================

@GetMapping("/admin/reviews/delete/{id}")
public String deleteReview(@PathVariable Long id) {

 restaurantReviewRepository.deleteById(id);

 return "redirect:/admin/reviews";
}
//==========================================
//ADMIN LOGIN PAGE
//==========================================

@GetMapping("/admin/login")
public String adminLogin() {

 return "admin-login";
}
}