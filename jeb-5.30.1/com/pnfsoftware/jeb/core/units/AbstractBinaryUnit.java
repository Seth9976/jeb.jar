package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class AbstractBinaryUnit extends AbstractUnit implements IBinaryUnit {
   @SerId(1)
   private String mimeType;
   @SerId(2)
   private IInput input;

   public AbstractBinaryUnit(String var1, IInput var2, String var3, String var4, IUnit var5) {
      this(var1, var2, var3, var4, var5.getUnitProcessor(), var5, var5.getPropertyDefinitionManager());
   }

   public AbstractBinaryUnit(String var1, IInput var2, String var3, String var4, IUnitProcessor var5, IUnitCreator var6, IPropertyDefinitionManager var7) {
      super(var3, var4, var5, var6, var7);
      if (var1 == null || var1.isEmpty()) {
         var1 = "application/octet-stream";
      }

      this.mimeType = var1;
      if (var2 == null) {
         throw new IllegalArgumentException("A binary unit is expected to contain binary data");
      } else {
         this.input = var2;
      }
   }

   @Override
   public String getMimeType() {
      return this.mimeType;
   }

   public void setMimeType(String var1) {
      this.mimeType = var1;
   }

   @Override
   public IInput getInput() {
      return this.input;
   }

   public void setInput(IInput var1) {
      this.input = var1;
   }
}
