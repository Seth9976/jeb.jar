package com.pnfsoftware.jeb.corei.parsers.image;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.encoding.MimeType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class Av extends AbstractBinaryUnit {
   public Av(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "image", var1, var3, var4, var5);
      this.setMimeType(MimeType.determineFromContent(var2));
   }

   @Override
   public boolean process() {
      this.setProcessed(true);
      return true;
   }
}
