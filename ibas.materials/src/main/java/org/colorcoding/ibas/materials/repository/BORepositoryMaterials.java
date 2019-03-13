package org.colorcoding.ibas.materials.repository;

import org.colorcoding.ibas.bobas.common.ConditionOperation;
import org.colorcoding.ibas.bobas.common.ConditionRelationship;
import org.colorcoding.ibas.bobas.common.Criteria;
import org.colorcoding.ibas.bobas.common.IChildCriteria;
import org.colorcoding.ibas.bobas.common.ICondition;
import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.common.ISort;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.bobas.common.SortType;
import org.colorcoding.ibas.bobas.data.ArrayList;
import org.colorcoding.ibas.bobas.data.DateTime;
import org.colorcoding.ibas.bobas.data.Decimal;
import org.colorcoding.ibas.bobas.data.List;
import org.colorcoding.ibas.bobas.data.emApprovalStatus;
import org.colorcoding.ibas.bobas.data.emDocumentStatus;
import org.colorcoding.ibas.bobas.data.emYesNo;
import org.colorcoding.ibas.bobas.i18n.I18N;
import org.colorcoding.ibas.bobas.repository.BORepositoryServiceApplication;
import org.colorcoding.ibas.materials.MyConfiguration;
import org.colorcoding.ibas.materials.bo.goodsissue.GoodsIssue;
import org.colorcoding.ibas.materials.bo.goodsissue.IGoodsIssue;
import org.colorcoding.ibas.materials.bo.goodsreceipt.GoodsReceipt;
import org.colorcoding.ibas.materials.bo.goodsreceipt.IGoodsReceipt;
import org.colorcoding.ibas.materials.bo.inventorycounting.IInventoryCounting;
import org.colorcoding.ibas.materials.bo.inventorycounting.IInventoryCountingLine;
import org.colorcoding.ibas.materials.bo.inventorycounting.InventoryCounting;
import org.colorcoding.ibas.materials.bo.inventorytransfer.IInventoryTransfer;
import org.colorcoding.ibas.materials.bo.inventorytransfer.InventoryTransfer;
import org.colorcoding.ibas.materials.bo.material.IMaterial;
import org.colorcoding.ibas.materials.bo.material.IMaterialGroup;
import org.colorcoding.ibas.materials.bo.material.IMaterialPrice;
import org.colorcoding.ibas.materials.bo.material.IMaterialQuantity;
import org.colorcoding.ibas.materials.bo.material.IProduct;
import org.colorcoding.ibas.materials.bo.material.Material;
import org.colorcoding.ibas.materials.bo.material.MaterialGroup;
import org.colorcoding.ibas.materials.bo.material.MaterialPrice;
import org.colorcoding.ibas.materials.bo.material.MaterialQuantity;
import org.colorcoding.ibas.materials.bo.material.Product;
import org.colorcoding.ibas.materials.bo.materialbatch.IMaterialBatch;
import org.colorcoding.ibas.materials.bo.materialbatch.IMaterialBatchJournal;
import org.colorcoding.ibas.materials.bo.materialbatch.MaterialBatch;
import org.colorcoding.ibas.materials.bo.materialbatch.MaterialBatchJournal;
import org.colorcoding.ibas.materials.bo.materialinventory.IMaterialEstimateJournal;
import org.colorcoding.ibas.materials.bo.materialinventory.IMaterialInventory;
import org.colorcoding.ibas.materials.bo.materialinventory.IMaterialInventoryJournal;
import org.colorcoding.ibas.materials.bo.materialinventory.MaterialEstimateJournal;
import org.colorcoding.ibas.materials.bo.materialinventory.MaterialInventory;
import org.colorcoding.ibas.materials.bo.materialinventory.MaterialInventoryJournal;
import org.colorcoding.ibas.materials.bo.materialpricelist.IMaterialPriceItem;
import org.colorcoding.ibas.materials.bo.materialpricelist.IMaterialPriceList;
import org.colorcoding.ibas.materials.bo.materialpricelist.MaterialPriceItem;
import org.colorcoding.ibas.materials.bo.materialpricelist.MaterialPriceList;
import org.colorcoding.ibas.materials.bo.materialserial.IMaterialSerial;
import org.colorcoding.ibas.materials.bo.materialserial.IMaterialSerialJournal;
import org.colorcoding.ibas.materials.bo.materialserial.MaterialSerial;
import org.colorcoding.ibas.materials.bo.materialserial.MaterialSerialJournal;
import org.colorcoding.ibas.materials.bo.warehouse.IWarehouse;
import org.colorcoding.ibas.materials.bo.warehouse.Warehouse;

/**
 * Materials仓库
 */
public class BORepositoryMaterials extends BORepositoryServiceApplication
		implements IBORepositoryMaterialsSvc, IBORepositoryMaterialsApp {

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-库存发货
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<GoodsIssue> fetchGoodsIssue(ICriteria criteria, String token) {
		return super.fetch(criteria, token, GoodsIssue.class);
	}

	/**
	 * 查询-库存发货（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<IGoodsIssue> fetchGoodsIssue(ICriteria criteria) {
		return new OperationResult<IGoodsIssue>(this.fetchGoodsIssue(criteria, this.getUserToken()));
	}

	/**
	 * 保存-库存发货
	 *
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	public OperationResult<GoodsIssue> saveGoodsIssue(GoodsIssue bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-库存发货（提前设置用户口令）
	 *
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IGoodsIssue> saveGoodsIssue(IGoodsIssue bo) {
		return new OperationResult<IGoodsIssue>(this.saveGoodsIssue((GoodsIssue) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-库存收货
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<GoodsReceipt> fetchGoodsReceipt(ICriteria criteria, String token) {
		return super.fetch(criteria, token, GoodsReceipt.class);
	}

	/**
	 * 查询-库存收货（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<IGoodsReceipt> fetchGoodsReceipt(ICriteria criteria) {
		return new OperationResult<IGoodsReceipt>(this.fetchGoodsReceipt(criteria, this.getUserToken()));
	}

	/**
	 * 保存-库存收货
	 *
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	public OperationResult<GoodsReceipt> saveGoodsReceipt(GoodsReceipt bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-库存收货（提前设置用户口令）
	 *
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IGoodsReceipt> saveGoodsReceipt(IGoodsReceipt bo) {
		return new OperationResult<IGoodsReceipt>(this.saveGoodsReceipt((GoodsReceipt) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-库存转储
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<InventoryTransfer> fetchInventoryTransfer(ICriteria criteria, String token) {
		return super.fetch(criteria, token, InventoryTransfer.class);
	}

	/**
	 * 查询-库存转储（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<IInventoryTransfer> fetchInventoryTransfer(ICriteria criteria) {
		return new OperationResult<IInventoryTransfer>(this.fetchInventoryTransfer(criteria, this.getUserToken()));
	}

	/**
	 * 保存-库存转储
	 *
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	public OperationResult<InventoryTransfer> saveInventoryTransfer(InventoryTransfer bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-库存转储（提前设置用户口令）
	 *
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IInventoryTransfer> saveInventoryTransfer(IInventoryTransfer bo) {
		return new OperationResult<IInventoryTransfer>(
				this.saveInventoryTransfer((InventoryTransfer) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-物料
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<Material> fetchMaterial(ICriteria criteria, String token) {
		return super.fetch(criteria, token, Material.class);
	}

	/**
	 * 查询-物料（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<IMaterial> fetchMaterial(ICriteria criteria) {
		return new OperationResult<IMaterial>(this.fetchMaterial(criteria, this.getUserToken()));
	}

	/**
	 * 保存-物料
	 *
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	public OperationResult<Material> saveMaterial(Material bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-物料（提前设置用户口令）
	 *
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IMaterial> saveMaterial(IMaterial bo) {
		return new OperationResult<IMaterial>(this.saveMaterial((Material) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-物料组
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<MaterialGroup> fetchMaterialGroup(ICriteria criteria, String token) {
		return super.fetch(criteria, token, MaterialGroup.class);
	}

	/**
	 * 查询-物料组（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<IMaterialGroup> fetchMaterialGroup(ICriteria criteria) {
		return new OperationResult<IMaterialGroup>(this.fetchMaterialGroup(criteria, this.getUserToken()));
	}

	/**
	 * 保存-物料组
	 *
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	public OperationResult<MaterialGroup> saveMaterialGroup(MaterialGroup bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-物料组（提前设置用户口令）
	 *
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IMaterialGroup> saveMaterialGroup(IMaterialGroup bo) {
		return new OperationResult<IMaterialGroup>(this.saveMaterialGroup((MaterialGroup) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-物料库存
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<MaterialInventory> fetchMaterialInventory(ICriteria criteria, String token) {
		return super.fetch(criteria, token, MaterialInventory.class);
	}

	/**
	 * 查询-物料库存（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<IMaterialInventory> fetchMaterialInventory(ICriteria criteria) {
		return new OperationResult<IMaterialInventory>(this.fetchMaterialInventory(criteria, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-仓库日记账
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<MaterialInventoryJournal> fetchMaterialInventoryJournal(ICriteria criteria, String token) {
		return super.fetch(criteria, token, MaterialInventoryJournal.class);
	}

	/**
	 * 查询-仓库日记账（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<IMaterialInventoryJournal> fetchMaterialInventoryJournal(ICriteria criteria) {
		return new OperationResult<IMaterialInventoryJournal>(
				this.fetchMaterialInventoryJournal(criteria, this.getUserToken()));
	}

	/**
	 * 保存-仓库日记账
	 *
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	public OperationResult<MaterialInventoryJournal> saveMaterialInventoryJournal(MaterialInventoryJournal bo,
			String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-仓库日记账（提前设置用户口令）
	 *
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IMaterialInventoryJournal> saveMaterialInventoryJournal(IMaterialInventoryJournal bo) {
		return new OperationResult<IMaterialInventoryJournal>(
				this.saveMaterialInventoryJournal((MaterialInventoryJournal) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-物料价格清单
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<MaterialPriceList> fetchMaterialPriceList(ICriteria criteria, String token) {
		return super.fetch(criteria, token, MaterialPriceList.class);
	}

	/**
	 * 查询-物料价格清单（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<IMaterialPriceList> fetchMaterialPriceList(ICriteria criteria) {
		return new OperationResult<IMaterialPriceList>(this.fetchMaterialPriceList(criteria, this.getUserToken()));
	}

	/**
	 * 保存-物料价格清单
	 *
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	public OperationResult<MaterialPriceList> saveMaterialPriceList(MaterialPriceList bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-物料价格清单（提前设置用户口令）
	 *
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IMaterialPriceList> saveMaterialPriceList(IMaterialPriceList bo) {
		return new OperationResult<IMaterialPriceList>(
				this.saveMaterialPriceList((MaterialPriceList) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-物料批次
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<MaterialBatch> fetchMaterialBatch(ICriteria criteria, String token) {
		return super.fetch(criteria, token, MaterialBatch.class);
	}

	/**
	 * 查询-物料批次（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<IMaterialBatch> fetchMaterialBatch(ICriteria criteria) {
		return new OperationResult<IMaterialBatch>(this.fetchMaterialBatch(criteria, this.getUserToken()));
	}

	/**
	 * 保存-物料批次
	 *
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	public OperationResult<MaterialBatch> saveMaterialBatch(MaterialBatch bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-物料批次（提前设置用户口令）
	 *
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IMaterialBatch> saveMaterialBatch(IMaterialBatch bo) {
		return new OperationResult<IMaterialBatch>(this.saveMaterialBatch((MaterialBatch) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-物料批次日记账
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	@Override
	public OperationResult<MaterialBatchJournal> fetchMaterialBatchJournal(ICriteria criteria, String token) {
		return super.fetch(criteria, token, MaterialBatchJournal.class);
	}

	/**
	 * 查询-物料批次日记账（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	@Override
	public IOperationResult<IMaterialBatchJournal> fetchMaterialBatchJournal(ICriteria criteria) {
		return new OperationResult<IMaterialBatchJournal>(
				this.fetchMaterialBatchJournal(criteria, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-物料序列
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	@Override
	public OperationResult<MaterialSerial> fetchMaterialSerial(ICriteria criteria, String token) {
		return super.fetch(criteria, token, MaterialSerial.class);
	}

	/**
	 * 查询-物料序列（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	@Override
	public IOperationResult<IMaterialSerial> fetchMaterialSerial(ICriteria criteria) {
		return new OperationResult<IMaterialSerial>(this.fetchMaterialSerial(criteria, this.getUserToken()));
	}

	/**
	 * 保存-物料序列
	 *
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	@Override
	public OperationResult<MaterialSerial> saveMaterialSerial(MaterialSerial bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-物料序列（提前设置用户口令）
	 *
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	@Override
	public IOperationResult<IMaterialSerial> saveMaterialSerial(IMaterialSerial bo) {
		return new OperationResult<IMaterialSerial>(this.saveMaterialSerial((MaterialSerial) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-物料序列日记账
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	@Override
	public OperationResult<MaterialSerialJournal> fetchMaterialSerialJournal(ICriteria criteria, String token) {
		return super.fetch(criteria, token, MaterialSerialJournal.class);
	}

	/**
	 * 查询-物料序列日记账（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	@Override
	public IOperationResult<IMaterialSerialJournal> fetchMaterialSerialJournal(ICriteria criteria) {
		return new OperationResult<IMaterialSerialJournal>(
				this.fetchMaterialSerialJournal(criteria, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

	/**
	 * 查询-仓库
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	@Override
	public OperationResult<Warehouse> fetchWarehouse(ICriteria criteria, String token) {
		return super.fetch(criteria, token, Warehouse.class);
	}

	/**
	 * 查询-仓库（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	@Override
	public IOperationResult<IWarehouse> fetchWarehouse(ICriteria criteria) {
		return new OperationResult<IWarehouse>(this.fetchWarehouse(criteria, this.getUserToken()));
	}

	/**
	 * 保存-仓库
	 *
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	@Override
	public OperationResult<Warehouse> saveWarehouse(Warehouse bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-仓库（提前设置用户口令）
	 *
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	@Override
	public IOperationResult<IWarehouse> saveWarehouse(IWarehouse bo) {
		return new OperationResult<IWarehouse>(this.saveWarehouse((Warehouse) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-仓库预估日记账
	 * 
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<MaterialEstimateJournal> fetchMaterialEstimateJournal(ICriteria criteria, String token) {
		return super.fetch(criteria, token, MaterialEstimateJournal.class);
	}

	/**
	 * 查询-仓库预估日记账（提前设置用户口令）
	 * 
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<IMaterialEstimateJournal> fetchMaterialEstimateJournal(ICriteria criteria) {
		return new OperationResult<IMaterialEstimateJournal>(
				this.fetchMaterialEstimateJournal(criteria, this.getUserToken()));
	}

	/**
	 * 保存-仓库预估日记账
	 * 
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	public OperationResult<MaterialEstimateJournal> saveMaterialEstimateJournal(MaterialEstimateJournal bo,
			String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-仓库预估日记账（提前设置用户口令）
	 * 
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IMaterialEstimateJournal> saveMaterialEstimateJournal(IMaterialEstimateJournal bo) {
		return new OperationResult<IMaterialEstimateJournal>(
				this.saveMaterialEstimateJournal((MaterialEstimateJournal) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-库存盘点
	 * 
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<InventoryCounting> fetchInventoryCounting(ICriteria criteria, String token) {
		return super.fetch(criteria, token, InventoryCounting.class);
	}

	/**
	 * 查询-库存盘点（提前设置用户口令）
	 * 
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<IInventoryCounting> fetchInventoryCounting(ICriteria criteria) {
		return new OperationResult<IInventoryCounting>(this.fetchInventoryCounting(criteria, this.getUserToken()));
	}

	/**
	 * 保存-库存盘点
	 * 
	 * @param bo    对象实例
	 * @param token 口令
	 * @return 操作结果
	 */
	public OperationResult<InventoryCounting> saveInventoryCounting(InventoryCounting bo, String token) {
		if (bo != null && bo.getDocumentStatus() == emDocumentStatus.CLOSED) {
			return new OperationResult<>(new Exception(I18N.prop("msg_mm_not_allowed_closed_data")));
		}
		return super.save(bo, token);
	}

	/**
	 * 保存-库存盘点（提前设置用户口令）
	 * 
	 * @param bo 对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IInventoryCounting> saveInventoryCounting(IInventoryCounting bo) {
		return new OperationResult<IInventoryCounting>(
				this.saveInventoryCounting((InventoryCounting) bo, this.getUserToken()));
	}

	/**
	 * 结算-库存盘点
	 * 
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	public OperationResult<String> closeInventoryCounting(ICriteria criteria, String token) {
		try {
			this.setCurrentUser(token);
			if (criteria == null || criteria.getConditions().size() <= 0) {
				throw new Exception(I18N.prop("msg_bobas_invaild_criteria"));
			}
			IOperationResult<IInventoryCounting> opRsltData = this.fetchInventoryCounting(criteria);
			if (opRsltData.getError() != null) {
				throw opRsltData.getError();
			}
			OperationResult<String> opRsltClose = new OperationResult<>();
			boolean trans = this.beginTransaction();
			try {
				for (IInventoryCounting data : opRsltData.getResultObjects()) {
					if (data.getDocumentStatus() == emDocumentStatus.CLOSED
							|| data.getDocumentStatus() == emDocumentStatus.PLANNED
							|| (data.getApprovalStatus() != emApprovalStatus.APPROVED
									&& data.getApprovalStatus() != emApprovalStatus.UNAFFECTED)
							|| data.getCanceled() == emYesNo.YES) {
						continue;
					}
					for (IInventoryCountingLine dataLine : data.getInventoryCountingLines()) {
						if (dataLine.getLineStatus() == emDocumentStatus.CLOSED
								|| dataLine.getLineStatus() == emDocumentStatus.PLANNED) {
							continue;
						}
						if (dataLine.getCounted() != emYesNo.YES) {
							throw new Exception(I18N.prop("msg_mm_document_not_counted", dataLine));
						}
						dataLine.setLineStatus(emDocumentStatus.CLOSED);
					}
					data.setDocumentStatus(emDocumentStatus.CLOSED);
					data.setPostingDate(DateTime.getToday());
					IOperationResult<IInventoryCounting> opRsltSave = super.save(data, token);
					if (opRsltSave.getError() != null) {
						throw opRsltSave.getError();
					}
				}
				if (trans) {
					this.commitTransaction();
				}
			} catch (Exception e) {
				if (trans) {
					this.rollbackTransaction();
				}
				throw e;
			}
			return opRsltClose;
		} catch (Exception e) {
			return new OperationResult<>(e);
		}
	}

	/**
	 * 结算-库存盘点（提前设置用户口令）
	 * 
	 * @param criteria 查询
	 * @return 操作结果
	 */
	public IOperationResult<String> closeInventoryCounting(ICriteria criteria) {
		return this.closeInventoryCounting(criteria, this.getUserToken());
	}
	// --------------------------------------------------------------------------------------------//

	/**
	 * 过滤查询条件
	 * 
	 * @param criteria 原始查询
	 * @param include  true，包含条件；false，不包含条件
	 * @param alias    条件名称
	 * @return
	 */
	protected ICriteria filterConditions(ICriteria criteria, boolean include, String... alias) {
		if (criteria == null || alias == null || alias.length == 0) {
			return criteria;
		}
		ICriteria tmpCriteria = criteria.clone();
		int cIndex = -1;
		criteria = new Criteria();
		for (int i = 0; i < tmpCriteria.getConditions().size(); i++) {
			ICondition condition = tmpCriteria.getConditions().get(i);
			boolean exist = false;
			for (String item : alias) {
				if (item.equalsIgnoreCase(condition.getAlias())) {
					exist = true;
					cIndex = i;
					break;
				}
			}
			boolean used = false;
			if (exist && include) {
				used = true;
			} else if (!exist && !include) {
				used = true;
			}
			if (used) {
				criteria.getConditions().add(condition);
			} else {
				if (cIndex < i && cIndex >= 0) {
					// 使用的查询以后条件，括号归集到最近使用查询
					if (condition.getBracketOpen() > 0) {
						ICondition tmpCondition = tmpCriteria.getConditions().get(cIndex);
						tmpCondition.setBracketOpen(tmpCondition.getBracketOpen() + condition.getBracketOpen());
					}
					if (condition.getBracketClose() > 0) {
						ICondition tmpCondition = tmpCriteria.getConditions().get(cIndex);
						tmpCondition.setBracketClose(tmpCondition.getBracketClose() + condition.getBracketClose());
					}
				} else {
					if (condition.getBracketOpen() > 0 && i + 1 < tmpCriteria.getConditions().size()) {
						ICondition tmpCondition = tmpCriteria.getConditions().get(i + 1);
						tmpCondition.setBracketOpen(tmpCondition.getBracketOpen() + condition.getBracketOpen());
					}
					if (condition.getBracketClose() > 0 && i + 1 < tmpCriteria.getConditions().size()) {
						ICondition tmpCondition = tmpCriteria.getConditions().get(i + 1);
						tmpCondition.setBracketClose(tmpCondition.getBracketClose() + condition.getBracketClose());
					}
				}
			}
		}
		for (ISort sort : tmpCriteria.getSorts()) {
			boolean exist = false;
			for (String item : alias) {
				if (item.equalsIgnoreCase(sort.getAlias())) {
					exist = true;
					break;
				}
			}
			boolean used = false;
			if (exist && include) {
				used = true;
			} else if (!exist && !include) {
				used = true;
			}
			if (used) {
				criteria.getSorts().add(sort);
			}
		}
		return criteria;
	}

	/**
	 * 查询-物料价格（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	@Override
	public IOperationResult<IMaterialPrice> fetchMaterialPrice(ICriteria criteria) {
		return new OperationResult<IMaterialPrice>(this.fetchMaterialPrice(criteria, this.getUserToken()));
	}

	/**
	 * 查询-物料价格
	 *
	 * @param criteria 查询（支持的查询条件，仅为ItemCode，ItemName，PriceList）
	 * @param token    口令
	 * @return 物料价格
	 */
	@Override
	public OperationResult<MaterialPrice> fetchMaterialPrice(ICriteria criteria, String token) {
		try {
			this.setUserToken(token);
			if (criteria == null || criteria.getConditions().isEmpty()) {
				throw new Exception(I18N.prop("msg_bobas_invaild_criteria"));
			}
			// 查询物料
			ICriteria maCriteria = this.filterConditions(criteria, true, MaterialPrice.CONDITION_ALIAS_ITEMCODE,
					MaterialPrice.CONDITION_ALIAS_ITEMNAME);
			maCriteria.setResultCount(criteria.getResultCount());
			for (ICondition condition : maCriteria.getConditions()) {
				if (MaterialPrice.CONDITION_ALIAS_ITEMCODE.equalsIgnoreCase(condition.getAlias())) {
					condition.setAlias(Material.PROPERTY_CODE.getName());
				} else if (MaterialPrice.CONDITION_ALIAS_ITEMNAME.equalsIgnoreCase(condition.getAlias())) {
					condition.setAlias(Material.PROPERTY_NAME.getName());
				}
			}
			if (maCriteria.getSorts()
					.firstOrDefault(c -> Material.PROPERTY_CODE.getName().equalsIgnoreCase(c.getAlias())) == null) {
				ISort sort = maCriteria.getSorts().create();
				sort.setAlias(Material.PROPERTY_CODE.getName());
				sort.setSortType(SortType.ASCENDING);
			}
			IOperationResult<IMaterial> opRsltMaterial = this.fetchMaterial(maCriteria);
			if (opRsltMaterial.getError() != null) {
				throw opRsltMaterial.getError();
			}
			OperationResult<MaterialPrice> operationResult = new OperationResult<>();
			operationResult.addResultObjects(MaterialPrice.create(opRsltMaterial.getResultObjects()));
			// 查询物料价格
			ICondition plCondition = criteria.getConditions()
					.firstOrDefault(c -> MaterialPrice.CONDITION_ALIAS_PRICELIST.equalsIgnoreCase(c.getAlias()));
			if (plCondition != null) {
				List<String> itemCodes = new ArrayList<String>(operationResult.getResultObjects().size());
				for (IMaterialPrice item : operationResult.getResultObjects()) {
					itemCodes.add(item.getItemCode());
				}
				// 重新查询价格
				List<IMaterialPrice> newPrices = this.getMaterialPrices(itemCodes,
						Integer.valueOf(plCondition.getValue()));
				for (IMaterialPrice item : operationResult.getResultObjects()) {
					item.setPrice(Decimal.MINUS_ONE);// 设置到未初始价格
					item.setSource(plCondition.getValue());
					for (IMaterialPrice newPrice : newPrices) {
						if (item.getItemCode().equals(newPrice.getItemCode())) {
							item.setPrice(newPrice.getPrice());
							item.setCurrency(newPrice.getCurrency());
						}
					}
				}
			}
			return operationResult;
		} catch (Exception e) {
			return new OperationResult<>(e);
		}
	}

	/**
	 * 查询物料对应价格清单的价格
	 *
	 * @param itemCodes 物料编码
	 * @param priceList 价格清单
	 * @param factory   价格清单系数
	 * @return
	 * @throws Exception
	 */
	private List<IMaterialPrice> getMaterialPrices(List<String> itemCodes, Integer priceList) throws Exception {
		return this.getMaterialPrices(itemCodes, priceList, 0);
	}

	/**
	 * 查询物料对应价格清单的价格
	 *
	 * @param itemCode  物料
	 * @param priceList 价格清单
	 * @param level     层级
	 * @return
	 * @throws Exception
	 */
	private List<IMaterialPrice> getMaterialPrices(List<String> itemCodes, Integer priceList, int level)
			throws Exception {
		level++;
		if (level > MyConfiguration.getConfigValue(MyConfiguration.CONFIG_ITEM_PRICE_LIST_MAX_LEVEL, 3)) {
			StringBuilder stringBuilder = new StringBuilder();
			for (String itemCode : itemCodes) {
				if (itemCode == null || itemCode.isEmpty()) {
					continue;
				}
				if (stringBuilder.length() > 0) {
					stringBuilder.append(", ");
				}
				stringBuilder.append(itemCode);
			}
			throw new Exception(I18N.prop("msg_mm_material_price_list_more_than_max_level", stringBuilder.toString()));
		}
		ICriteria criteria = new Criteria();
		ICondition condition = criteria.getConditions().create();
		condition.setAlias(MaterialPriceList.PROPERTY_OBJECTKEY.getName());
		condition.setValue(priceList);
		IChildCriteria childCriteria = criteria.getChildCriterias().create();
		childCriteria.setPropertyPath(MaterialPriceList.PROPERTY_MATERIALPRICEITEMS.getName());
		childCriteria.setOnlyHasChilds(false);
		for (int i = 0; i < itemCodes.size(); i++) {
			String itemCode = itemCodes.get(i);
			if (itemCode == null || itemCode.isEmpty()) {
				continue;
			}
			condition = childCriteria.getConditions().create();
			condition.setAlias(MaterialPriceItem.PROPERTY_ITEMCODE.getName());
			condition.setOperation(ConditionOperation.EQUAL);
			condition.setValue(itemCode);
			condition.setRelationship(ConditionRelationship.OR);
			// 修正条件参数
			if (i == 0) {
				// 第一个
				condition.setRelationship(ConditionRelationship.AND);
				condition.setBracketOpen(1);
			}
			if (i == itemCodes.size() - 1) {
				// 最后一个
				condition.setBracketClose(1);
			}
		}
		IOperationResult<IMaterialPriceList> opRsltPriceList = this.fetchMaterialPriceList(criteria);
		if (opRsltPriceList.getError() != null) {
			throw opRsltPriceList.getError();
		}
		List<IMaterialPrice> newPrices = new ArrayList<>();
		IMaterialPriceList materialPriceList = opRsltPriceList.getResultObjects().firstOrDefault();
		if (materialPriceList == null) {
			// 价格清单未找到
			return newPrices;
		} else {
			List<String> noItemCodes = new ArrayList<>();
			for (String itemCode : itemCodes) {
				if (itemCode == null || itemCode.isEmpty()) {
					continue;
				}
				IMaterialPriceItem priceItem = materialPriceList.getMaterialPriceItems()
						.firstOrDefault(c -> itemCode.equalsIgnoreCase(c.getItemCode()));
				if (priceItem != null) {
					// 价格清单定义了价格
					newPrices.add(MaterialPrice.create(priceItem, materialPriceList.getCurrency()));
				} else {
					noItemCodes.add(itemCode);
				}
			}
			if (!noItemCodes.isEmpty() && materialPriceList.getBasedOnList() != null
					&& materialPriceList.getBasedOnList() > 0 && materialPriceList.getBasedOnList() != priceList) {
				List<IMaterialPrice> tmpPrices = this.getMaterialPrices(noItemCodes, materialPriceList.getBasedOnList(),
						level);
				for (IMaterialPrice newPrice : tmpPrices) {
					if (!Decimal.isZero(materialPriceList.getFactor())) {
						newPrice.setPrice(newPrice.getPrice().multiply(materialPriceList.getFactor()));
					}
					newPrices.add(newPrice);
				}
			}
		}
		return newPrices;
	}

	/**
	 * 查询-物料数量（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 操作结果
	 */
	@Override
	public IOperationResult<IMaterialQuantity> fetchMaterialQuantity(ICriteria criteria) {
		return new OperationResult<IMaterialQuantity>(this.fetchMaterialQuantity(criteria, this.getUserToken()));
	}

	/**
	 * 查询-物料数量
	 *
	 * @param criteria 查询（支持的查询条件，仅为ItemCode，ItemName，WhsCode）
	 * @param token    口令
	 * @return 物料库存数量
	 */
	@Override
	public OperationResult<MaterialQuantity> fetchMaterialQuantity(ICriteria criteria, String token) {
		try {
			this.setUserToken(token);
			if (criteria == null || criteria.getConditions().isEmpty()) {
				throw new Exception(I18N.prop("msg_bobas_invaild_criteria"));
			}
			// 查询物料
			ICriteria maCriteria = this.filterConditions(criteria, true, MaterialQuantity.CONDITION_ALIAS_ITEMCODE,
					MaterialQuantity.CONDITION_ALIAS_ITEMNAME);
			maCriteria.setResultCount(criteria.getResultCount());
			for (ICondition condition : maCriteria.getConditions()) {
				if (MaterialQuantity.CONDITION_ALIAS_ITEMCODE.equalsIgnoreCase(condition.getAlias())) {
					condition.setAlias(Material.PROPERTY_CODE.getName());
				} else if (MaterialQuantity.CONDITION_ALIAS_ITEMNAME.equalsIgnoreCase(condition.getAlias())) {
					condition.setAlias(Material.PROPERTY_NAME.getName());
				}
			}
			if (maCriteria.getSorts()
					.firstOrDefault(c -> Material.PROPERTY_CODE.getName().equalsIgnoreCase(c.getAlias())) == null) {
				ISort sort = maCriteria.getSorts().create();
				sort.setAlias(Material.PROPERTY_CODE.getName());
				sort.setSortType(SortType.ASCENDING);
			}
			IOperationResult<IMaterial> opRsltMaterial = this.fetchMaterial(maCriteria);
			if (opRsltMaterial.getError() != null) {
				throw opRsltMaterial.getError();
			}
			OperationResult<MaterialQuantity> operationResult = new OperationResult<>();
			operationResult.addResultObjects(MaterialQuantity.create(opRsltMaterial.getResultObjects()));
			// 查询物料库存
			ICriteria whCriteria = this.filterConditions(criteria, true, MaterialQuantity.CONDITION_ALIAS_WAREHOUSE);
			if (whCriteria != null && !whCriteria.getConditions().isEmpty()) {
				// 添加物料的条件
				for (int i = 0; i < operationResult.getResultObjects().size(); i++) {
					IMaterialQuantity item = operationResult.getResultObjects().get(i);
					ICondition condition = whCriteria.getConditions().create();
					condition.setAlias(MaterialQuantity.CONDITION_ALIAS_ITEMCODE);
					condition.setOperation(ConditionOperation.EQUAL);
					condition.setValue(item.getItemCode());
					condition.setRelationship(ConditionRelationship.OR);
					// 修正条件参数
					if (i == 0) {
						// 第一个
						condition.setRelationship(ConditionRelationship.AND);
						condition.setBracketOpen(1);
					}
					if (i == operationResult.getResultObjects().size() - 1) {
						// 最后一个
						condition.setBracketClose(1);
					}
				}
				IOperationResult<IMaterialInventory> opRsltInventory = this.fetchMaterialInventory(whCriteria);
				if (opRsltInventory.getError() != null) {
					throw opRsltInventory.getError();
				}
				for (IMaterialQuantity item : operationResult.getResultObjects()) {
					// 数量清零
					item.setOnHand(Decimal.ZERO);
					item.setOnCommited(Decimal.ZERO);
					item.setOnOrdered(Decimal.ZERO);
					// 重新计算数量
					for (IMaterialInventory inventory : opRsltInventory.getResultObjects()) {
						if (item.getItemCode().equals(inventory.getItemCode())) {
							item.setOnHand(item.getOnHand().add(inventory.getOnHand()));
							item.setOnCommited(item.getOnCommited().add(inventory.getOnCommited()));
							item.setOnOrdered(item.getOnOrdered().add(inventory.getOnOrdered()));
						}
					}
				}
			}
			return operationResult;
		} catch (Exception e) {
			return new OperationResult<>(e);
		}
	}

	/**
	 * 查询-产品信息（提前设置用户口令）
	 *
	 * @param criteria 查询
	 * @return 产品信息
	 */
	@Override
	public IOperationResult<IProduct> fetchProduct(ICriteria criteria) {
		return new OperationResult<IProduct>(this.fetchProduct(criteria, this.getUserToken()));
	}

	/**
	 * 查询-产品信息
	 *
	 * @param criteria 查询
	 * @param token    口令
	 * @return 操作结果
	 */
	@Override
	public OperationResult<Product> fetchProduct(ICriteria criteria, String token) {
		try {
			this.setUserToken(token);
			if (criteria == null || criteria.getConditions().isEmpty()) {
				throw new Exception(I18N.prop("msg_bobas_invaild_criteria"));
			}
			// 查询物料
			ICriteria pdCriteria = this.filterConditions(criteria, false, Product.CONDITION_ALIAS_WAREHOUSE,
					Product.CONDITION_ALIAS_PRICELIST);
			pdCriteria.setResultCount(criteria.getResultCount());
			OperationResult<Product> operationResult = this.fetch(pdCriteria, token, Product.class);
			if (operationResult.getError() != null) {
				throw operationResult.getError();
			}
			// 查询物料的库存
			ICriteria whCriteria = this.filterConditions(criteria, true, Product.CONDITION_ALIAS_WAREHOUSE);
			if (whCriteria != null && !whCriteria.getConditions().isEmpty()) {
				for (int i = 0; i < operationResult.getResultObjects().size(); i++) {
					IProduct product = operationResult.getResultObjects().get(i);
					ICondition condition = whCriteria.getConditions().create();
					condition.setAlias(MaterialQuantity.CONDITION_ALIAS_ITEMCODE);
					condition.setOperation(ConditionOperation.EQUAL);
					condition.setValue(product.getCode());
					condition.setRelationship(ConditionRelationship.OR);
					if (i == 0) {
						// 第一个条件
						condition.setBracketOpen(1);
						condition.setRelationship(ConditionRelationship.AND);
					}
					if (i == operationResult.getResultObjects().size() - 1) {
						// 最后条件
						condition.setBracketClose(1);
					}
				}
				IOperationResult<IMaterialInventory> opRsltInventory = this.fetchMaterialInventory(whCriteria);
				if (opRsltInventory.getError() != null) {
					throw opRsltInventory.getError();
				}
				for (IProduct item : operationResult.getResultObjects()) {
					// 数量清零
					item.setOnHand(Decimal.ZERO);
					item.setOnCommited(Decimal.ZERO);
					item.setOnOrdered(Decimal.ZERO);
					// 重新计算数量
					for (IMaterialInventory inventory : opRsltInventory.getResultObjects()) {
						if (item.getCode().equals(inventory.getItemCode())) {
							item.setOnHand(item.getOnHand().add(inventory.getOnHand()));
							item.setOnCommited(item.getOnCommited().add(inventory.getOnCommited()));
							item.setOnOrdered(item.getOnOrdered().add(inventory.getOnOrdered()));
							if (item.getWarehouse() == null) {
								item.setWarehouse("");
							}
							if (!item.getWarehouse().isEmpty()) {
								item.setWarehouse(item.getWarehouse() + ",");
							}
							item.setWarehouse(item.getWarehouse() + inventory.getWarehouse());
						}
					}
				}
			}
			// 查询物料的价格
			ICondition plCondition = criteria.getConditions()
					.firstOrDefault(c -> MaterialPrice.CONDITION_ALIAS_PRICELIST.equalsIgnoreCase(c.getAlias()));
			if (plCondition != null) {
				List<String> itemCodes = new ArrayList<String>(operationResult.getResultObjects().size());
				for (IProduct item : operationResult.getResultObjects()) {
					itemCodes.add(item.getCode());
				}
				// 重新查询价格
				List<IMaterialPrice> newPrices = this.getMaterialPrices(itemCodes,
						Integer.valueOf(plCondition.getValue()));
				for (IProduct item : operationResult.getResultObjects()) {
					item.setPrice(Decimal.MINUS_ONE);// 设置到未初始价格
					for (IMaterialPrice newPrice : newPrices) {
						if (item.getCode().equals(newPrice.getItemCode())) {
							item.setPrice(newPrice.getPrice());
							item.setCurrency(newPrice.getCurrency());
						}
					}
				}
			}
			return operationResult;
		} catch (Exception e) {
			return new OperationResult<>(e);
		}
	}
	// --------------------------------------------------------------------------------------------//
}