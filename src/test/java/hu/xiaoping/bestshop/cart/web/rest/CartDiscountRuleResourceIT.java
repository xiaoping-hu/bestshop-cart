package hu.xiaoping.bestshop.cart.web.rest;

import hu.xiaoping.bestshop.cart.CartApp;
import hu.xiaoping.bestshop.cart.config.SecurityBeanOverrideConfiguration;
import hu.xiaoping.bestshop.cart.domain.CartDiscountRule;
import hu.xiaoping.bestshop.cart.repository.CartDiscountRuleRepository;
import hu.xiaoping.bestshop.cart.service.CartDiscountRuleService;

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
 * Integration tests for the {@link CartDiscountRuleResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, CartApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class CartDiscountRuleResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_MINIMUM_QUANTITY = 1;
    private static final Integer UPDATED_MINIMUM_QUANTITY = 2;

    private static final Integer DEFAULT_DISCOUNT_QUANTITY = 1;
    private static final Integer UPDATED_DISCOUNT_QUANTITY = 2;

    private static final Integer DEFAULT_DISCOUNT_AMOUNT = 1;
    private static final Integer UPDATED_DISCOUNT_AMOUNT = 2;

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    @Autowired
    private CartDiscountRuleRepository cartDiscountRuleRepository;

    @Autowired
    private CartDiscountRuleService cartDiscountRuleService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCartDiscountRuleMockMvc;

    private CartDiscountRule cartDiscountRule;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CartDiscountRule createEntity(EntityManager em) {
        CartDiscountRule cartDiscountRule = new CartDiscountRule()
            .name(DEFAULT_NAME)
            .minimumQuantity(DEFAULT_MINIMUM_QUANTITY)
            .discountQuantity(DEFAULT_DISCOUNT_QUANTITY)
            .discountAmount(DEFAULT_DISCOUNT_AMOUNT)
            .priority(DEFAULT_PRIORITY);
        return cartDiscountRule;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CartDiscountRule createUpdatedEntity(EntityManager em) {
        CartDiscountRule cartDiscountRule = new CartDiscountRule()
            .name(UPDATED_NAME)
            .minimumQuantity(UPDATED_MINIMUM_QUANTITY)
            .discountQuantity(UPDATED_DISCOUNT_QUANTITY)
            .discountAmount(UPDATED_DISCOUNT_AMOUNT)
            .priority(UPDATED_PRIORITY);
        return cartDiscountRule;
    }

    @BeforeEach
    public void initTest() {
        cartDiscountRule = createEntity(em);
    }

    @Test
    @Transactional
    public void createCartDiscountRule() throws Exception {
        int databaseSizeBeforeCreate = cartDiscountRuleRepository.findAll().size();
        // Create the CartDiscountRule
        restCartDiscountRuleMockMvc.perform(post("/api/cart-discount-rules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRule)))
            .andExpect(status().isCreated());

        // Validate the CartDiscountRule in the database
        List<CartDiscountRule> cartDiscountRuleList = cartDiscountRuleRepository.findAll();
        assertThat(cartDiscountRuleList).hasSize(databaseSizeBeforeCreate + 1);
        CartDiscountRule testCartDiscountRule = cartDiscountRuleList.get(cartDiscountRuleList.size() - 1);
        assertThat(testCartDiscountRule.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCartDiscountRule.getMinimumQuantity()).isEqualTo(DEFAULT_MINIMUM_QUANTITY);
        assertThat(testCartDiscountRule.getDiscountQuantity()).isEqualTo(DEFAULT_DISCOUNT_QUANTITY);
        assertThat(testCartDiscountRule.getDiscountAmount()).isEqualTo(DEFAULT_DISCOUNT_AMOUNT);
        assertThat(testCartDiscountRule.getPriority()).isEqualTo(DEFAULT_PRIORITY);
    }

    @Test
    @Transactional
    public void createCartDiscountRuleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cartDiscountRuleRepository.findAll().size();

        // Create the CartDiscountRule with an existing ID
        cartDiscountRule.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCartDiscountRuleMockMvc.perform(post("/api/cart-discount-rules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRule)))
            .andExpect(status().isBadRequest());

        // Validate the CartDiscountRule in the database
        List<CartDiscountRule> cartDiscountRuleList = cartDiscountRuleRepository.findAll();
        assertThat(cartDiscountRuleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cartDiscountRuleRepository.findAll().size();
        // set the field null
        cartDiscountRule.setName(null);

        // Create the CartDiscountRule, which fails.


        restCartDiscountRuleMockMvc.perform(post("/api/cart-discount-rules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRule)))
            .andExpect(status().isBadRequest());

        List<CartDiscountRule> cartDiscountRuleList = cartDiscountRuleRepository.findAll();
        assertThat(cartDiscountRuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMinimumQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = cartDiscountRuleRepository.findAll().size();
        // set the field null
        cartDiscountRule.setMinimumQuantity(null);

        // Create the CartDiscountRule, which fails.


        restCartDiscountRuleMockMvc.perform(post("/api/cart-discount-rules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRule)))
            .andExpect(status().isBadRequest());

        List<CartDiscountRule> cartDiscountRuleList = cartDiscountRuleRepository.findAll();
        assertThat(cartDiscountRuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDiscountQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = cartDiscountRuleRepository.findAll().size();
        // set the field null
        cartDiscountRule.setDiscountQuantity(null);

        // Create the CartDiscountRule, which fails.


        restCartDiscountRuleMockMvc.perform(post("/api/cart-discount-rules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRule)))
            .andExpect(status().isBadRequest());

        List<CartDiscountRule> cartDiscountRuleList = cartDiscountRuleRepository.findAll();
        assertThat(cartDiscountRuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDiscountAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = cartDiscountRuleRepository.findAll().size();
        // set the field null
        cartDiscountRule.setDiscountAmount(null);

        // Create the CartDiscountRule, which fails.


        restCartDiscountRuleMockMvc.perform(post("/api/cart-discount-rules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRule)))
            .andExpect(status().isBadRequest());

        List<CartDiscountRule> cartDiscountRuleList = cartDiscountRuleRepository.findAll();
        assertThat(cartDiscountRuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriorityIsRequired() throws Exception {
        int databaseSizeBeforeTest = cartDiscountRuleRepository.findAll().size();
        // set the field null
        cartDiscountRule.setPriority(null);

        // Create the CartDiscountRule, which fails.


        restCartDiscountRuleMockMvc.perform(post("/api/cart-discount-rules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRule)))
            .andExpect(status().isBadRequest());

        List<CartDiscountRule> cartDiscountRuleList = cartDiscountRuleRepository.findAll();
        assertThat(cartDiscountRuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCartDiscountRules() throws Exception {
        // Initialize the database
        cartDiscountRuleRepository.saveAndFlush(cartDiscountRule);

        // Get all the cartDiscountRuleList
        restCartDiscountRuleMockMvc.perform(get("/api/cart-discount-rules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cartDiscountRule.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].minimumQuantity").value(hasItem(DEFAULT_MINIMUM_QUANTITY)))
            .andExpect(jsonPath("$.[*].discountQuantity").value(hasItem(DEFAULT_DISCOUNT_QUANTITY)))
            .andExpect(jsonPath("$.[*].discountAmount").value(hasItem(DEFAULT_DISCOUNT_AMOUNT)))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)));
    }
    
    @Test
    @Transactional
    public void getCartDiscountRule() throws Exception {
        // Initialize the database
        cartDiscountRuleRepository.saveAndFlush(cartDiscountRule);

        // Get the cartDiscountRule
        restCartDiscountRuleMockMvc.perform(get("/api/cart-discount-rules/{id}", cartDiscountRule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cartDiscountRule.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.minimumQuantity").value(DEFAULT_MINIMUM_QUANTITY))
            .andExpect(jsonPath("$.discountQuantity").value(DEFAULT_DISCOUNT_QUANTITY))
            .andExpect(jsonPath("$.discountAmount").value(DEFAULT_DISCOUNT_AMOUNT))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY));
    }
    @Test
    @Transactional
    public void getNonExistingCartDiscountRule() throws Exception {
        // Get the cartDiscountRule
        restCartDiscountRuleMockMvc.perform(get("/api/cart-discount-rules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCartDiscountRule() throws Exception {
        // Initialize the database
        cartDiscountRuleService.save(cartDiscountRule);

        int databaseSizeBeforeUpdate = cartDiscountRuleRepository.findAll().size();

        // Update the cartDiscountRule
        CartDiscountRule updatedCartDiscountRule = cartDiscountRuleRepository.findById(cartDiscountRule.getId()).get();
        // Disconnect from session so that the updates on updatedCartDiscountRule are not directly saved in db
        em.detach(updatedCartDiscountRule);
        updatedCartDiscountRule
            .name(UPDATED_NAME)
            .minimumQuantity(UPDATED_MINIMUM_QUANTITY)
            .discountQuantity(UPDATED_DISCOUNT_QUANTITY)
            .discountAmount(UPDATED_DISCOUNT_AMOUNT)
            .priority(UPDATED_PRIORITY);

        restCartDiscountRuleMockMvc.perform(put("/api/cart-discount-rules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCartDiscountRule)))
            .andExpect(status().isOk());

        // Validate the CartDiscountRule in the database
        List<CartDiscountRule> cartDiscountRuleList = cartDiscountRuleRepository.findAll();
        assertThat(cartDiscountRuleList).hasSize(databaseSizeBeforeUpdate);
        CartDiscountRule testCartDiscountRule = cartDiscountRuleList.get(cartDiscountRuleList.size() - 1);
        assertThat(testCartDiscountRule.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCartDiscountRule.getMinimumQuantity()).isEqualTo(UPDATED_MINIMUM_QUANTITY);
        assertThat(testCartDiscountRule.getDiscountQuantity()).isEqualTo(UPDATED_DISCOUNT_QUANTITY);
        assertThat(testCartDiscountRule.getDiscountAmount()).isEqualTo(UPDATED_DISCOUNT_AMOUNT);
        assertThat(testCartDiscountRule.getPriority()).isEqualTo(UPDATED_PRIORITY);
    }

    @Test
    @Transactional
    public void updateNonExistingCartDiscountRule() throws Exception {
        int databaseSizeBeforeUpdate = cartDiscountRuleRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCartDiscountRuleMockMvc.perform(put("/api/cart-discount-rules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDiscountRule)))
            .andExpect(status().isBadRequest());

        // Validate the CartDiscountRule in the database
        List<CartDiscountRule> cartDiscountRuleList = cartDiscountRuleRepository.findAll();
        assertThat(cartDiscountRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCartDiscountRule() throws Exception {
        // Initialize the database
        cartDiscountRuleService.save(cartDiscountRule);

        int databaseSizeBeforeDelete = cartDiscountRuleRepository.findAll().size();

        // Delete the cartDiscountRule
        restCartDiscountRuleMockMvc.perform(delete("/api/cart-discount-rules/{id}", cartDiscountRule.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CartDiscountRule> cartDiscountRuleList = cartDiscountRuleRepository.findAll();
        assertThat(cartDiscountRuleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
