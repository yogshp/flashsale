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
@Table(name = "refunds")
public class Refund {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;

	@Column(name = "orderId")
	@NotNull
	private Long orderId;

	@Column(name = "amount")
	@NotNull
	private Double amount;

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

	public Refund() {

	}

	public Refund(@NotNull Long orderId, @NotNull Double amount) {
		this.orderId = orderId;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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
		return "Refund [id=" + id + ", orderId=" + orderId + ", amount=" + amount + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
