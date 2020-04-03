package br.com.compasso.votacao.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.compasso.votacao.enumeration.ScheduleStatusEnum;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	@Enumerated(EnumType.STRING)
	private ScheduleStatusEnum status;
	private LocalDateTime createdAt = LocalDateTime.now();
	@OneToOne
	private Associate author;
	private Integer votesYes = 0;
	private Integer votesNo = 0;

	public Schedule() {}
	
	public Schedule(String title, String description) {
		this.title = title;
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ScheduleStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatusEnum status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Associate getAuthor() {
		return author;
	}

	public void setAuthor(Associate author) {
		this.author = author;
	}

	public Integer getVotesYes() {
		return votesYes;
	}

	public void setVotesYes(Integer votesYes) {
		this.votesYes = votesYes;
	}

	public Integer getVotesNo() {
		return votesNo;
	}

	public void setVotesNo(Integer votesNo) {
		this.votesNo = votesNo;
	}

}
