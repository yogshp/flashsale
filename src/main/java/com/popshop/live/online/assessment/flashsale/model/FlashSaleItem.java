package com.popshop.live.online.assessment.flashsale.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "flash_sale_items")
public class FlashSaleItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "price")
	@NotNull
	private Double price;

	@Column(name = "quantity")
	@NotNull
	private Integer quantity;

	@Column(name = "begin_at")
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime beginAt;

	@Column(name = "end_at")
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endAt;

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

	public FlashSaleItem() {

	}

	public FlashSaleItem(@NotNull String name, @NotNull Double price, @NotNull Integer quantity,
			@NotNull LocalDateTime beginAt, @NotNull LocalDateTime endAt) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.beginAt = beginAt;
		this.endAt = endAt;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getBeginAt() {
		return beginAt;
	}

	public void setBeginAt(LocalDateTime beginAt) {
		this.beginAt = beginAt;
	}

	public LocalDateTime getEndAt() {
		return endAt;
	}

	public void setEndAt(LocalDateTime endAt) {
		this.endAt = endAt;
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
		return "FlashSaleItem [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", beginAt=" + beginAt + ", endAt=" + endAt + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}

}
