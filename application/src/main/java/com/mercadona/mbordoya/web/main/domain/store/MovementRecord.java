package com.mercadona.mbordoya.web.main.domain.store;

public record MovementRecord (Long productId, MovementTypeEnum movementType, Integer quantity){

  public boolean isVE() {
    return MovementTypeEnum.VE.equals(this.movementType);
  }

  public boolean isRO() {
    return MovementTypeEnum.RO.equals(this.movementType);
  }

  public boolean isVEOrRO() {
    return this.isVE() || this.isRO();
  }

  public boolean isEA() {
    return MovementTypeEnum.EA.equals(this.movementType);
  }
}
