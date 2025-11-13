package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.units.IBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;

public class cqd {
   private static final ILogger q = GlobalLog.getLogger(cqd.class);

   public static IUnit q(IUnit var0) {
      if (!(var0 instanceof IBinaryUnit)) {
         return null;
      } else {
         try {
            IUnit var9;
            try (SeekableByteChannel var1 = ((IBinaryUnit)var0).getInput().getChannel()) {
               long var2 = ChannelUtil.getBELong(var1, 0L);
               if (var2 == -8552249625308161526L) {
                  return null;
               }

               byte[] var4 = new byte[14];
               int var5 = var4.length + (int)var1.size();
               ByteBuffer var6 = ByteBuffer.wrap(var4);
               var6.order(ByteOrder.LITTLE_ENDIAN);
               var6.put((byte)66);
               var6.put((byte)77);
               var6.putInt(var5);
               var6.putShort((short)0);
               var6.putShort((short)0);
               int var7 = ChannelUtil.getLEInt(var1, 0L);
               var6.putInt(var4.length + var7);
               BytePipe var8 = new BytePipe(var5);
               var8.append(var4);
               var8.append(ChannelUtil.getAllFrom(var1, 0L));
               var9 = var0.getUnitProcessor().process("image", new BytesInput(var8.getAll()), var0);
            }

            return var9;
         } catch (IOException var12) {
            q.catching(var12);
            return null;
         }
      }
   }
}
