package cn.lidongsports.system.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * 数据字典
 */
@Entity
@Table(name="sys_dict")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Dictionary {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	// 名称：民族
	private String itemName;
	// 值：汉族、回族等
	private String itemValue;
	// 描述
	private String note;
	// 排序
	private int sn;
	
	public Dictionary() {
	}
	
	public Dictionary(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

}
