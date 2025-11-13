package com.pnfsoftware.jeb.core.actions;

import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.units.IInteractiveUnit;
import com.pnfsoftware.jeb.util.format.Strings;

public class ActionContext {
   private IInteractiveUnit unit;
   private int actionId;
   private long itemId;
   private String address;
   private AddressConversionPrecision addressPrecision;

   public ActionContext(IInteractiveUnit var1, int var2, long var3) {
      this(var1, var2, var3, null, AddressConversionPrecision.DEFAULT);
   }

   public ActionContext(IInteractiveUnit var1, int var2, long var3, String var5) {
      this(var1, var2, var3, var5, AddressConversionPrecision.DEFAULT);
   }

   public ActionContext(IInteractiveUnit var1, int var2, long var3, String var5, AddressConversionPrecision var6) {
      if (var1 != null && var2 != 0) {
         this.unit = var1;
         this.actionId = var2;
         this.itemId = var3;
         this.address = var5;
         this.addressPrecision = var6;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public IInteractiveUnit getUnit() {
      return this.unit;
   }

   public void setUnit(IInteractiveUnit var1) {
      this.unit = var1;
   }

   public int getActionId() {
      return this.actionId;
   }

   public void setActionId(int var1) {
      this.actionId = var1;
   }

   public long getItemId() {
      return this.itemId;
   }

   public void setItemId(long var1) {
      this.itemId = var1;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String var1) {
      this.address = var1;
   }

   public AddressConversionPrecision getAddressPrecision() {
      return this.addressPrecision;
   }

   public void setAddressPrecision(AddressConversionPrecision var1) {
      this.addressPrecision = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("unit=%s,actionId=%s,itemId=%d,address=%s", this.unit, this.actionId, this.itemId, this.address);
   }
}
