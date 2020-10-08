package kr.or.connect.reservation.dao;

public class ReservationDaoSqls {
	public static final String SELECT_PAGING =
			"SELECT display_info.id AS displayInfoId, product.id AS productId, product.description AS productDescription, display_info.place_name AS placeName, "
					+ "product.content AS productContent, file_info.save_file_name AS productImageUrl\n" +
				"FROM product \n" + 
				"JOIN product_image ON product.id = product_image.product_id\n" +
				"JOIN display_info ON product.id = display_info.product_id\n" +
				"JOIN file_info ON  product_image.file_id = file_info.id\n" +
				"WHERE product.category_id = :categoryId \n" +
				"AND product_image.type = 'th'\n" +
			"ORDER BY display_info.id limit :start, :limit;";
	
	public static final String SELECT_PAGING_ALL_CATEGORIES =
			"SELECT display_info.id AS displayInfoId, product.id AS productId, product.description AS productDescription, display_info.place_name AS placeName, "
					+ "product.content AS productContent, file_info.save_file_name AS productImageUrl\n" +
				"FROM product \n" + 
				"JOIN product_image ON product.id = product_image.product_id\n" +
				"JOIN display_info ON product.id = display_info.product_id\n" +
				"JOIN file_info ON  product_image.file_id = file_info.id\n" +
				"WHERE product.category_id > :categoryId \n" +
				"AND product_image.type = 'th'\n" +
			"ORDER BY display_info.id limit :start, :limit;";
	
	public static final String SELECT_CATEGORIES = 
			"SELECT product.category_id AS id, category.name AS name,  COUNT(*) AS count\n" +
				"FROM display_info\n" + 
				"JOIN product ON product.id = display_info.product_id\n" + 
				"JOIN category ON product.category_id = category.id\n" + 
				"GROUP BY category.id;";
	
	public static final String SELECT_PROMOTIONS = 
			"SELECT promotion.id AS id, promotion.product_id AS productId, file_info.save_file_name AS productImageUrl\n" + 
				"FROM promotion, product, product_image, file_info\n" +  
				"WHERE promotion.product_id = product.id \n" + 
				"AND product.id  = product_image.product_id \n" + 
				"AND product_image.file_id  = file_info.id\n" + 
				"AND product_image.type='th';";
	
//	public static final String DELETE_BY_ID = "DELETE FROM guestbook WHERE id = :id";
	public static final String SELECT_COUNT = 
			"SELECT COUNT(*) as count\n" + 
				"FROM product \n" + 
				"JOIN display_info ON product.id = display_info.product_id\n" + 
				"JOIN display_info_image ON display_info.id = display_info_image.display_info_id\n" + 
				"JOIN file_info ON  file_info.id = display_info_image.file_id\n" +
				"WHERE product.category_id = :categoryId \n" ;
	
	public static final String SELECT_COUNT_ALL_CATEGORIES = 
			"SELECT COUNT(*) as count\n" + 
				"FROM product \n" + 
				"JOIN display_info ON product.id = display_info.product_id\n" + 
				"JOIN display_info_image ON display_info.id = display_info_image.display_info_id\n" + 
				"JOIN file_info ON  file_info.id = display_info_image.file_id\n" +
				"WHERE product.category_id > :categoryId \n" ;
}
