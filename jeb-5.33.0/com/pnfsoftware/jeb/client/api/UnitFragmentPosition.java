package com.pnfsoftware.jeb.client.api;

import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.output.IItem;
import com.pnfsoftware.jeb.core.units.IUnit;

public class UnitFragmentPosition {
   IUnit unit;
   IUnitFragment fragment;
   IGenericDocument doc;
   String address;
   IItem item;

   public UnitFragmentPosition(IUnit var1, IUnitFragment var2, IGenericDocument var3, String var4, IItem var5) {
      this.unit = var1;
      this.fragment = var2;
      this.doc = var3;
      this.address = var4;
      this.item = var5;
   }

   public IUnit getUnit() {
      return this.unit;
   }

   public IUnitFragment getFragment() {
      return this.fragment;
   }

   public IGenericDocument getDoc() {
      return this.doc;
   }

   public String getAddress() {
      return this.address;
   }

   public IItem getItem() {
      return this.item;
   }

   @Override
   public String toString() {
      return this.unit + " " + this.fragment + " " + this.doc + " " + this.address + " " + this.item;
   }
}
