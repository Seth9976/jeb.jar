package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.encoding.MimeType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.nio.ByteBuffer;

@Ser
public class cdw extends AbstractBinaryUnit {
   public cdw(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "generic", var1, var3, var4, var5);
      this.setMimeType(MimeType.determineFromContent(var2));
   }

   @Override
   public boolean process() {
      if (this.getInput().getCurrentSize() == 16L) {
         ByteBuffer var1 = this.getInput().getHeader();
         if (var1.limit() >= 16 && var1.getLong() == 7474411459012790955L && var1.getLong() == 2313978730093534497L) {
            throw new JebRuntimeException("This is a test failure");
         }
      }

      this.setProcessed(true);
      return true;
   }
}
