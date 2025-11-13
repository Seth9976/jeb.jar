package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.input.LazyInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.util.encoding.MimeType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;

@Ser
public class UnknownBinaryUnit extends AbstractBinaryUnit {
   @SerTransient
   private IUnit knownUnit = null;
   @SerId(1)
   private String hintWantedType = null;

   public UnknownBinaryUnit(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "unknown", var1, var3, var4, var5);
      if (!(var2 instanceof LazyInput)) {
         this.setMimeType(MimeType.determineFromContent(var2));
      }
   }

   @Override
   public boolean process() {
      if (this.isProcessed()) {
         return true;
      } else {
         IUnit var1 = this.getUnitProcessor().process(this.getName(), this.getInput(), this, this.hintWantedType, true);
         if (var1 != null && !var1.getFormatType().equals("generic")) {
            if (this.getParent() instanceof AbstractUnit && var1 instanceof AbstractBinaryUnit && ((AbstractBinaryUnit)var1).getInput() == this.getInput()) {
               AbstractUnit var2 = (AbstractUnit)this.getParent();
               var1.setParent(var2);
               var2.setChild(this, var1);
               this.knownUnit = var1;
               return true;
            }

            this.addChild(var1);
         }

         this.setProcessed(true);
         return true;
      }
   }

   public void setHintWantedType(String var1) {
      this.hintWantedType = var1;
   }

   public IUnit getKnownUnit() {
      return this.knownUnit;
   }
}
