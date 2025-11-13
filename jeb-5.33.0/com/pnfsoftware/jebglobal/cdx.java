package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IDuplicatedUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.encoding.MimeType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class cdx extends AbstractBinaryUnit implements IDuplicatedUnit {
   @SerId(1)
   private IUnit pC;

   public cdx(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5, IUnit var6) {
      super(null, var2, "duplicate", var1, var3, var4, var5);
      this.setMimeType(MimeType.determineFromContent(var2));
      this.pC = var6;
   }

   @Override
   public boolean process() {
      this.setProcessed(true);
      return true;
   }

   @Override
   public String getDescription() {
      String var1 = super.getDescription();
      return var1 + "- " + Strings.ff(S.L("Duplicate of %s"), UnitUtil.buildFullyQualifiedUnitPath(this.pC));
   }

   @Override
   public IUnit getOriginal() {
      return this.pC;
   }
}
