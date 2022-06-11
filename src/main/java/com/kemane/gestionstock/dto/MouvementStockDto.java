package com.kemane.gestionstock.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.kemane.gestionstock.model.MouvementStock;
import com.kemane.gestionstock.model.TypeMouvementStock;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MouvementStockDto {

	private int id;
	
	private Instant dateMouvement;
	
	private BigDecimal quantity;

	private ArticleDto article;
	
	private TypeMouvementStock typeMouvementStock;

	private Integer idEntreprise;

	public static MouvementStockDto fromEntity(MouvementStock mouvementStock) {
		if(mouvementStock == null) {
			return null;
		}
		return  MouvementStockDto.builder()
				.id(mouvementStock.getId())
				.idEntreprise(mouvementStock.getIdEntreprise())
				.article(ArticleDto.fromEntity(mouvementStock.getArticle()))
				.dateMouvement(mouvementStock.getDateMouvement())
				.quantity(mouvementStock.getQuantity())
				.build();
	}

	public static MouvementStock toEntity(MouvementStockDto dto) {
		if (dto == null) {
			return null;
		}

		MouvementStock mvtStk = new MouvementStock();
		mvtStk.setId(dto.getId());
		mvtStk.setDateMouvement(dto.getDateMouvement());
		mvtStk.setQuantity(dto.getQuantity());
		mvtStk.setIdEntreprise(dto.getIdEntreprise());
		mvtStk.setArticle(ArticleDto.toEntity(dto.getArticle()));
		mvtStk.setTypeMouvementStock(dto.getTypeMouvementStock());
		return mvtStk;
	}
}
