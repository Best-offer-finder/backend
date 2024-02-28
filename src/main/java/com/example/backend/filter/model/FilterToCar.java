package com.example.backend.filter.model;

import com.example.backend.filter.CarStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "filter_to_car", uniqueConstraints = @UniqueConstraint(columnNames = {"car_id", "filter_id"}))
public class FilterToCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "car_id", nullable = false)
    private long carId;

    @ManyToOne
    @JoinColumn(name = "filter_id", nullable = false)
    private Filter filter;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "search_type")
    private CarStatus status;

    public static FilterToCar of(Filter filter, long carId, CarStatus carStatus) {
        FilterToCar ftc = new FilterToCar();
        ftc.setCarId(carId);
        ftc.setCreatedAt(OffsetDateTime.now());
        ftc.setFilter(filter);
        ftc.setStatus(carStatus);
        return ftc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }
}
