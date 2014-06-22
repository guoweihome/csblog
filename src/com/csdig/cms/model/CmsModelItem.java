package com.csdig.cms.model;

public class CmsModelItem implements java.io.Serializable {

	private static final long serialVersionUID = 0L;
	// public static final String DB_NAME = "user";

	// columns START
	/**
	 * modelItemId db_column: model_item_id
	 */
	private java.lang.Integer modelItemId;
	/**
	 * modelId db_column: model_id
	 */
	private java.lang.Integer modelId;
	/**
	 * �ֶ� db_column: field
	 */
	private java.lang.String field;
	/**
	 * ��� db_column: item_label
	 */
	private java.lang.String itemLabel;
	/**
	 * ����˳�� db_column: priority
	 */
	private java.lang.Integer priority;
	/**
	 * Ĭ��ֵ db_column: def_value
	 */
	private java.lang.String defValue;
	/**
	 * ��ѡ�� db_column: opt_value
	 */
	private java.lang.String optValue;
	/**
	 * ������� 1.�ı� 2.ͼƬ 3.���� db_column: data_type
	 */
	private java.lang.Integer dataType;
	/**
	 * �Ƿ񵥶�һ�� 1.�� 0.���� db_column: is_single
	 */
	private java.lang.Boolean isSingle;
	/**
	 * �Ƿ���� 1.�� 0.���� db_column: is_required
	 */
	private java.lang.Boolean isRequired;

	// columns END

	// constructors
	public CmsModelItem() {
	}

	public CmsModelItem(Integer modelItemId, Integer modelId, String field, String itemLabel, Integer priority,
			String defValue, String optValue, Integer dataType, Boolean isSingle, Boolean isRequired) {
		this.modelItemId = modelItemId;
		this.modelId = modelId;
		this.field = field;
		this.itemLabel = itemLabel;
		this.priority = priority;
		this.defValue = defValue;
		this.optValue = optValue;
		this.dataType = dataType;
		this.isSingle = isSingle;
		this.isRequired = isRequired;
	}
	
	public void setModelItemId(java.lang.Integer value) {
		this.modelItemId = value;
	}

	public java.lang.Integer getModelItemId() {
		return this.modelItemId;
	}

	public void setModelId(java.lang.Integer value) {
		this.modelId = value;
	}

	public java.lang.Integer getModelId() {
		return this.modelId;
	}

	public void setField(java.lang.String value) {
		this.field = value;
	}

	public java.lang.String getField() {
		return this.field;
	}

	public void setItemLabel(java.lang.String value) {
		this.itemLabel = value;
	}

	public java.lang.String getItemLabel() {
		return this.itemLabel;
	}

	public void setPriority(java.lang.Integer value) {
		this.priority = value;
	}

	public java.lang.Integer getPriority() {
		return this.priority;
	}

	public void setDefValue(java.lang.String value) {
		this.defValue = value;
	}

	public java.lang.String getDefValue() {
		return this.defValue;
	}

	public void setOptValue(java.lang.String value) {
		this.optValue = value;
	}

	public java.lang.String getOptValue() {
		return this.optValue;
	}

	public void setDataType(java.lang.Integer value) {
		this.dataType = value;
	}

	public java.lang.Integer getDataType() {
		return this.dataType;
	}

	public void setIsSingle(java.lang.Boolean value) {
		this.isSingle = value;
	}

	public java.lang.Boolean getIsSingle() {
		return this.isSingle;
	}

	public void setIsRequired(java.lang.Boolean value) {
		this.isRequired = value;
	}

	public java.lang.Boolean getIsRequired() {
		return this.isRequired;
	}

}
