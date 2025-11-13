package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Ser
public class AddressPrefixMetadataGroup extends AbstractMetadataGroup {
   public static final String DEFAULT_NAME = "byAddressPrefix";
   @SerId(1)
   IAddressableUnit unit;
   @SerId(2)
   private Map map = new HashMap();
   @SerId(3)
   List blacklist = new ArrayList();

   public AddressPrefixMetadataGroup(IAddressableUnit var1) {
      this(var1, null);
   }

   public AddressPrefixMetadataGroup(IAddressableUnit var1, String var2) {
      super(var2 != null ? var2 : "byAddressPrefix", MetadataGroupType.OPAQUE);
      this.unit = var1;
   }

   @Override
   public Map getAllData() {
      return Collections.unmodifiableMap(this.map);
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
   public Object getData(String var1) {
      return this.getData(var1, AddressConversionPrecision.DEFAULT);
   }

   @Override
   public Object getData(String var1, AddressConversionPrecision var2) {
      if (var1 == null) {
         return null;
      } else {
         var1 = this.unit.getCanonicalAddress(var1);
         if (var1 == null) {
            return null;
         } else {
            for (Entry var4 : this.getAllData().entrySet()) {
               String var5 = (String)var4.getKey();
               if (var1.startsWith(var5)) {
                  for (String var7 : this.blacklist) {
                     if (var1.startsWith(var7)) {
                        return null;
                     }
                  }

                  return var4.getValue();
               }
            }

            return null;
         }
      }
   }

   public List getBlacklist() {
      return this.blacklist;
   }

   @Override
   public List getSectionAnchorIds() {
      return null;
   }
}
