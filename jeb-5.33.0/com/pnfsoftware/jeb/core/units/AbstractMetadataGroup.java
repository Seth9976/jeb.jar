package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class AbstractMetadataGroup implements IMetadataGroup {
   @SerId(1)
   protected String name;
   @SerId(2)
   protected MetadataGroupType type;

   protected AbstractMetadataGroup(String var1, MetadataGroupType var2) {
      this.name = var1;
      this.type = var2;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public MetadataGroupType getType() {
      return this.type;
   }
}
