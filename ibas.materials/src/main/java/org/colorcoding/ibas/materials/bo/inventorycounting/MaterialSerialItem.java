package org.colorcoding.ibas.materials.bo.inventorycounting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.colorcoding.ibas.bobas.data.Decimal;
import org.colorcoding.ibas.bobas.data.emDirection;
import org.colorcoding.ibas.bobas.logic.IBusinessLogicContract;
import org.colorcoding.ibas.materials.MyConfiguration;
import org.colorcoding.ibas.materials.logic.IMaterialSerialJournalContract;

/**
 * 物料序列项目（库存转储）
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = MaterialSerialItem.BUSINESS_OBJECT_NAME, namespace = MyConfiguration.NAMESPACE_BO)
class MaterialSerialItem extends org.colorcoding.ibas.materials.bo.materialserial.MaterialSerialItem {

	private static final long serialVersionUID = -6562828886914294810L;

	public static final String BUSINESS_OBJECT_NAME = "MaterialSerialItemC";

	InventoryCountingLine parent;

	@Override
	public IBusinessLogicContract[] getContracts() {
		if (Decimal.ZERO.compareTo(MaterialSerialItem.this.parent.getDifference()) > 0) {
			// 小于0，发货
			return new IBusinessLogicContract[] {

					new IMaterialSerialJournalContract() {

						@Override
						public emDirection getDirection() {
							return emDirection.OUT;
						}

						@Override
						public String getWarehouse() {
							return MaterialSerialItem.this.parent.getWarehouse();
						}

						@Override
						public String getIdentifiers() {
							return MaterialSerialItem.this.getIdentifiers();
						}

						@Override
						public String getItemCode() {
							return MaterialSerialItem.this.parent.getItemCode();
						}

						@Override
						public String getDocumentType() {
							return MaterialSerialItem.this.getDocumentType();
						}

						@Override
						public Integer getDocumentLineId() {
							return MaterialSerialItem.this.getDocumentLineId();
						}

						@Override
						public Integer getDocumentEntry() {
							return MaterialSerialItem.this.getDocumentEntry();
						}

						@Override
						public String getSerialCode() {
							return MaterialSerialItem.this.getSerialCode();
						}
					}

			};
		} else if (Decimal.ZERO.compareTo(MaterialSerialItem.this.parent.getDifference()) < 0) {
			// 小于0，收货
			return new IBusinessLogicContract[] {

					new IMaterialSerialJournalContract() {

						@Override
						public emDirection getDirection() {
							return emDirection.IN;
						}

						@Override
						public String getWarehouse() {
							return MaterialSerialItem.this.parent.getWarehouse();
						}

						@Override
						public String getIdentifiers() {
							return MaterialSerialItem.this.getIdentifiers();
						}

						@Override
						public String getItemCode() {
							return MaterialSerialItem.this.parent.getItemCode();
						}

						@Override
						public String getDocumentType() {
							return MaterialSerialItem.this.getDocumentType();
						}

						@Override
						public Integer getDocumentLineId() {
							return MaterialSerialItem.this.getDocumentLineId();
						}

						@Override
						public Integer getDocumentEntry() {
							return MaterialSerialItem.this.getDocumentEntry();
						}

						@Override
						public String getSerialCode() {
							return MaterialSerialItem.this.getSerialCode();
						}
					}

			};
		}
		return null;
	}
}
