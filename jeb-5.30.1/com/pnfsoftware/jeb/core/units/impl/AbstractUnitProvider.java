package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.core.units.IUnitProvider;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public abstract class AbstractUnitProvider implements IUnitProvider {
   @SerId(1)
   private String formatType;
   @SerId(2)
   private String description;

   public AbstractUnitProvider(String var1, String var2, List var3) {
      this.formatType = var1;
      this.description = var2;
   }

   @Override
   public String getFormatType() {
      return this.formatType;
   }

   @Override
   public String getDescription() {
      return this.description;
   }
}
