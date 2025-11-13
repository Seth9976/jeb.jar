package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import java.util.List;

public abstract class AbstractValueComposite implements ITypedValue {
   private long objectId;
   protected long refTypeId;

   public AbstractValueComposite(long var1, long var3) {
      this.objectId = var1;
      this.refTypeId = var3;
   }

   public long getObjectId() {
      return this.objectId;
   }

   public long getRefTypeId() {
      return this.refTypeId;
   }

   @Override
   public String format() {
      return this.toString();
   }

   public abstract List getValue();

   public boolean hasChildren() {
      List var1 = this.getValue();
      return var1 != null && var1.size() > 0;
   }
}
