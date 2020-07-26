package hu.xiaoping.bestshop.cart.web.rest;

import hu.xiaoping.bestshop.cart.CartApp;
import hu.xiaoping.bestshop.cart.config.SecurityBeanOverrideConfiguration;
import hu.xiaoping.bestshop.cart.domain.CartDiscountRuleItem;
import hu.xiaoping.bestshop.cart.repository.CartDiscountRuleItemRepository;
import hu.xiaoping.bestshop.cart.service.CartDiscountRuleItemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CartDiscountRuleItemResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, CartApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class CartDiscountRuleItemResourceIT {

    private static final Integer DEFAULT_PRODUCT_ID = 1;
    private static final Integer UPDATED_PRODUCT_ID = 2;

    @Autowired
    private CartDiscountRuleItemRepository cartDiscountRuleItemRepository;

    @Autowired
    private CartDiscountRuleItemService cartDiscountRuleItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCartDiscountRuleItemMockMvc;

    private CartDiscountRuleItem cartDiscountRuleItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CartDiscountRuleItem createEntity(EntityManager em) {
        CartDiscountRuleItem cartDiscountRuleItem = new CartDiscountRuleItem()
            .productId(DEFAULT_PRODUCT_ID);
        return cartDiscountRuleItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CartDiscountRuleItem createUpdatedEntity(EntityManager em) {
        CartDiscountRuleItem cartDiscountRuleItem = new CartDiscountRuleItem()
            .productId(UPDATED_PRODUCT_ID);
        return cartDiscountRuleItem;
    }

    @BeforeEach
    public void initTest() {
        cartDiscountRuleItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createCartDiscountRuleItem() throws Exception {
        int databaseSizeBeforeCreate = cartDiscountRuleItemRepository.findAll().size();
        // Create the CartDiscountRuleItem
        restCartDiscountRuleItemMockMvc.perform(post("/api/cart-discount-rule-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRuleItem)))
            .andExpect(status().isCreated());

        // Validate the CartDiscountRuleItem in the database
        List<CartDiscountRuleItem> cartDiscountRuleItemList = cartDiscountRuleItemRepository.findAll();
        assertThat(cartDiscountRuleItemList).hasSize(databaseSizeBeforeCreate + 1);
        CartDiscountRuleItem testCartDiscountRuleItem = cartDiscountRuleItemList.get(cartDiscountRuleItemList.size() - 1);
        assertThat(testCartDiscountRuleItem.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
    }

    @Test
    @Transactional
    public void createCartDiscountRuleItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cartDiscountRuleItemRepository.findAll().size();

        // Create the CartDiscountRuleItem with an existing ID
        cartDiscountRuleItem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCartDiscountRuleItemMockMvc.perform(post("/api/cart-discount-rule-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRuleItem)))
            .andExpect(status().isBadRequest());

        // Validate the CartDiscountRuleItem in the database
        List<CartDiscountRuleItem> cartDiscountRuleItemList = cartDiscountRuleItemRepository.findAll();
        assertThat(cartDiscountRuleItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = cartDiscountRuleItemRepository.findAll().size();
        // set the field null
        cartDiscountRuleItem.setProductId(null);

        // Create the CartDiscountRuleItem, which fails.


        restCartDiscountRuleItemMockMvc.perform(post("/api/cart-discount-rule-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRuleItem)))
            .andExpect(status().isBadRequest());

        List<CartDiscountRuleItem> cartDiscountRuleItemList = cartDiscountRuleItemRepository.findAll();
        assertThat(cartDiscountRuleItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCartDiscountRuleItems() throws Exception {
        // Initialize the database
        cartDiscountRuleItemRepository.saveAndFlush(cartDiscountRuleItem);

        // Get all the cartDiscountRuleItemList
        restCartDiscountRuleItemMockMvc.perform(get("/api/cart-discount-rule-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cartDiscountRuleItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)));
    }
    
    @Test
    @Transactional
    public void getCartDiscountRuleItem() throws Exception {
        // Initialize the database
        cartDiscountRuleItemRepository.saveAndFlush(cartDiscountRuleItem);

        // Get the cartDiscountRuleItem
        restCartDiscountRuleItemMockMvc.perform(get("/api/cart-discount-rule-items/{id}", cartDiscountRuleItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cartDiscountRuleItem.getId().intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID));
    }
    @Test
    @Transactional
    public void getNonExistingCartDiscountRuleItem() throws Exception {
        // Get the cartDiscountRuleItem
        restCartDiscountRuleItemMockMvc.perform(get("/api/cart-discount-rule-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCartDiscountRuleItem() throws Exception {
        // Initialize the database
        cartDiscountRuleItemService.save(cartDiscountRuleItem);

        int databaseSizeBeforeUpdate = cartDiscountRuleItemRepository.findAll().size();

        // Update the cartDiscountRuleItem
        CartDiscountRuleItem updatedCartDiscountRuleItem = cartDiscountRuleItemRepository.findById(cartDiscountRuleItem.getId()).get();
        // Disconnect from session so that the updates on updatedCartDiscountRuleItem are not directly saved in db
        em.detach(updatedCartDiscountRuleItem);
        updatedCartDiscountRuleItem
            .productId(UPDATED_PRODUCT_ID);

        restCartDiscountRuleItemMockMvc.perform(put("/api/cart-discount-rule-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCartDiscountRuleItem)))
            .andExpect(status().isOk());

        // Validate the CartDiscountRuleItem in the database
        List<CartDiscountRuleItem> cartDiscountRuleItemList = cartDiscountRuleItemRepository.findAll();
        assertThat(cartDiscountRuleItemList).hasSize(databaseSizeBeforeUpdate);
        CartDiscountRuleItem testCartDiscountRuleItem = cartDiscountRuleItemList.get(cartDiscountRuleItemList.size() - 1);
        assertThat(testCartDiscountRuleItem.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCartDiscountRuleItem() throws Exception {
        int databaseSizeBeforeUpdate = cartDiscountRuleItemRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCartDiscountRuleItemMockMvc.perform(put("/api/cart-discount-rule-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRuleItem)))
            .andExpect(status().isBadRequest());

        // Validate the CartDiscountRuleItem in the database
        List<CartDiscountRuleItem> cartDiscountRuleItemList = cartDiscountRuleItemRepository.findAll();
        assertThat(cartDiscountRuleItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCartDiscountRuleItem() throws Exception {
        // Initialize the database
        cartDiscountRuleItemService.save(cartDiscountRuleItem);

        int databaseSizeBeforeDelete = cartDiscountRuleItemRepository.findAll().size();

        // Delete the cartDiscountRuleItem
        restCartDiscountRuleItemMockMvc.perform(delete("/api/cart-discount-rule-items/{id}", cartDiscountRuleItem.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CartDiscountRuleItem> cartDiscountRuleItemList = cartDiscountRuleItemRepository.findAll();
        assertThat(cartDiscountRuleItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
