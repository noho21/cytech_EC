package jp.co.sss.cytech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.co.sss.cytech.entity.SalesItem;

public interface SalesItemRepository extends JpaRepository<SalesItem, Integer>{
	@Query("SELECT s " +
			  "FROM SalesItem s " +
			  "WHERE s.startMonth <= CURRENT_DATE " +
			  "AND s.endMonth >= CURRENT_DATE"
	)
			List<SalesItem> findActiveSalesItems();
}
