package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class MetadataGroup extends AbstractMetadataGroup {
   @SerId(1)
   private Map map = new HashMap();

   public MetadataGroup(String var1, MetadataGroupType var2) {
      super(var1, var2);
   }

   @Override
   public Map getAllData() {
      return Collections.unmodifiableMap(this.map);
   }

   @Override
   public Object getData(String var1, AddressConversionPrecision var2) {
      return this.map.get(var1);
   }

   @Override
   public Object getData(String var1) {
      return this.getData(var1, AddressConversionPrecision.DEFAULT);
   }

   @Override
   public boolean setData(String var1, Object var2) {
      if (var2 == null) {
         return this.map.remove(var1) != null;
      } else {
         this.map.put(var1, var2);
         return true;
      }
   }

   @Override
   public List getSectionAnchorIds() {
      return null;
   }
}
