package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_COUNT;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_COUNT_ALL_CATEGORIES;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_PAGING;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_PAGING_ALL_CATEGORIES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Product;

@Repository
public class ProductDao {
	 private NamedParameterJdbcTemplate jdbc;
	    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	    public ProductDao(DataSource dataSource) {
	        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	    }
	    
	    public List<Product> selectAll(Integer categoryId, Integer start, Integer limit) {
    		Map<String, Integer> params = new HashMap<>();
    		params.put("categoryId", categoryId);
    		params.put("start", start);
    		params.put("limit", limit);
    		
	    	if (categoryId == 0) {
	    		return jdbc.query(SELECT_PAGING_ALL_CATEGORIES, params, rowMapper);
	    	}
	        return jdbc.query(SELECT_PAGING, params, rowMapper);
	    }

		public int selectCount(Integer categoryId) {
			Map<String, Integer> params = new HashMap<>();
			params.put("categoryId", categoryId);

			if (categoryId == 0) {
				return jdbc.queryForObject(SELECT_COUNT_ALL_CATEGORIES, params, Integer.class);
			}
			return jdbc.queryForObject(SELECT_COUNT, params, Integer.class);
		}
		

}
