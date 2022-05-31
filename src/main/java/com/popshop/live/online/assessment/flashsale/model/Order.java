package com.popshop.live.online.assessment.flashsale.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.popshop.live.online.assessment.flashsale.enums.OrderStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "flash_sale_event_id")
	@NotNull
	private Long flashSaleEventId;

	@Column(name = "user_id")
	@NotNull
	private Long userId;

	@Column(name = "flash_sale_item_id")
	@NotNull
	private Long flashSaleItemId;

	@Column(name = "quantity")
	@NotNull
	private Integer quantity;

	@Column(name = "amount")
	@NotNull
	private Double amount;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@NotNull
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private OrderStatus status = OrderStatus.OPEN;

	@CreationTimestamp
	@Column(name = "created_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private LocalDateTime updatedAt;

	public Order() {

	}

	public Order(@NotNull String name, @NotNull Long flashSaleEventId, @NotNull Long userId,
			@NotNull Long flashSaleItemId, @NotNull Integer quantity, @NotNull Double amount) {
		this.name = name;
		this.flashSaleEventId = flashSaleEventId;
		this.userId = userId;
		this.flashSaleItemId = flashSaleItemId;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getFlashSaleEventId() {
		return flashSaleEventId;
	}

	public void setFlashSaleEventId(Long flashSaleEventId) {
		this.flashSaleEventId = flashSaleEventId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFlashSaleItemId() {
		return flashSaleItemId;
	}

	public void setFlashSaleItemId(Long flashSaleItemId) {
		this.flashSaleItemId = flashSaleItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", flashSaleEventId=" + flashSaleEventId + ", userId=" + userId
				+ ", flashSaleItemId=" + flashSaleItemId + ", quantity=" + quantity + ", amount=" + amount + ", status="
				+ status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
