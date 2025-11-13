package com.pnfsoftware.jeb.core.units;

import java.util.HashMap;
import java.util.Map;

public class UnitChangeEventData {
   public static final int NameUpdate = 1;
   public static final int LabelUpdate = 2;
   public static final int CommentUpdate = 3;
   public static final int CreatedPackage = 4;
   public static final int MovedTo = 5;
   public static final int Conversion = 6;
   public static final int CodeAddition = 7;
   public static final int StructuralChange = 8;
   public static final int NonStructuralChange = 9;
   public int type;
   public Object target;
   public Object value;
   public Object previousValue;
   public Object location;
   public Map attrmap = new HashMap();

   public UnitChangeEventData(int var1, Object var2) {
      this(var1, var2, null);
   }

   public UnitChangeEventData(int var1, Object var2, Object var3) {
      this(var1, var2, var3, null);
   }

   public UnitChangeEventData(int var1, Object var2, Object var3, Object var4) {
      this(var1, var2, var3, var4, null);
   }

   public UnitChangeEventData(int var1, Object var2, Object var3, Object var4, Object var5) {
      this.type = var1;
      this.target = var2;
      this.value = var3;
      this.previousValue = var4;
      this.location = var5;
   }

   public UnitChangeEventData attr(Object var1, Object var2) {
      this.attrmap.put(var1, var2);
      return this;
   }

   @Override
   public String toString() {
      return "UnitChangeEventData [type="
         + this.type
         + ", target="
         + this.target
         + ", value="
         + this.value
         + ", previousValue="
         + this.previousValue
         + ", location="
         + this.location
         + ", attrmap="
         + this.attrmap
         + "]";
   }
}
