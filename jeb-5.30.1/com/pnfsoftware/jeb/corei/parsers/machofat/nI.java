package com.pnfsoftware.jeb.corei.parsers.machofat;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.corei.parsers.macho.iZ;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.util.Arrays;

@Ser
public class nI extends AbstractBinaryUnit {
   private static final ILogger xK = GlobalLog.getLogger(nI.class);
   @SerId(1)
   CU q;
   @SerId(2)
   eo[] RF;

   public nI(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "machofat", var1, var3, var4, var5);
   }

   @Override
   public boolean canBePersisted() {
      return true;
   }

   @Override
   protected boolean processInternal() {
      try {
         try (
            SeekableByteChannel var1 = this.getInput().getChannel();
            InputStream var2 = this.getInput().getStream();
         ) {
            ByteBuffer var3 = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN);
            if (var1.read(var3) != 8) {
               return false;
            }

            var3.rewind();
            this.q = CU.q(var3);
            byte[] var4 = IO.readInputStream(var2);
            this.RF = new eo[this.q.RF];
            var3 = ByteBuffer.allocate(20).order(ByteOrder.BIG_ENDIAN);

            for (int var5 = 0; var5 < this.q.RF; var5++) {
               if (var1.read(var3) != 20) {
                  return false;
               }

               var3.rewind();
               eo var6 = eo.q(var3);
               var3.rewind();
               this.RF[var5] = var6;
               String var7 = Strings.ff("img%02d-%s-%s", var5 + 1, iZ.q(var6.q), iZ.q(var6.q, var6.RF));
               byte[] var8 = Arrays.copyOfRange(var4, var6.xK, var6.xK + var6.Dw);
               IUnit var9 = this.getUnitProcessor().process(var7, new BytesInput(var8), this);
               if (var9 != null) {
                  this.addChild(var9);
               }
            }
         }

         return true;
      } catch (IOException var14) {
         Object[] var10000 = new Object[]{var14};
         return false;
      }
   }
}
