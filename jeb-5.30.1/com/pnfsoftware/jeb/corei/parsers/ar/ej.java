package com.pnfsoftware.jeb.corei.parsers.ar;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IArchiveUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Ser
public class ej extends AbstractBinaryUnit implements IArchiveUnit {
   private static final ILogger gO = GlobalLog.getLogger(ej.class);
   private static final int nf = 8;
   @SerId(1)
   CU q = null;
   @SerId(2)
   eo RF = null;
   @SerId(3)
   CU xK = null;
   @SerId(4)
   nI Dw = null;
   @SerId(5)
   CU Uv = null;
   @SerId(6)
   private int gP;
   @SerId(7)
   List oW = null;

   public ej(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "ar", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      try (SeekableByteChannel var1 = this.getInput().getChannel()) {
         if (ChannelUtil.getLELong(var1, 0L) != 738142165265366049L) {
            return false;
         } else {
            int var2 = 8;
            this.q = this.q(var1, var2, "/               ");
            if (this.q != null) {
               if (!this.q(var1)) {
                  return false;
               }

               var2 = var2 + 60 + this.RF.q();
               if (var2 % 2 != 0) {
                  var2++;
               }
            }

            this.xK = this.q(var1, var2, "/               ");
            if (this.xK != null) {
               if (!this.q(var1, var2)) {
                  return false;
               }

               var2 = var2 + 60 + this.Dw.q();
               if (var2 % 2 != 0) {
                  var2++;
               }
            }

            this.Uv = this.q(var1, var2, "//              ");
            if (this.Uv != null) {
               int var3 = Integer.parseInt(Strings.decodeASCII(this.Uv.gO, 0, 10).trim());
               if (var3 != 0) {
                  this.gP = var2 + 60;
               }

               var2 = var2 + 60 + var3;
               if (var2 % 2 != 0) {
                  var2++;
               }
            }

            this.oW = new ArrayList();
            int var14 = var2;

            while (true) {
               CU var4 = this.q(var1, var14, null);
               if (var4 == null) {
                  return true;
               }

               this.oW.add(var4);
               String var5 = Strings.decodeASCII(var4.RF, 0, 16);
               if (var5.startsWith("/")) {
                  int var6 = Integer.parseInt(var5.substring(1).trim());
                  var5 = this.q(var6);
               }

               var5 = var5.trim();
               if (var5.endsWith("/")) {
                  var5 = var5.substring(0, var5.length() - 1);
               }

               int var18 = Integer.parseInt(Strings.decodeASCII(var4.gO, 0, 10).trim());
               Object[] var20 = new Object[]{var5, var14, var18};
               ByteBuffer var7 = ByteBuffer.allocate(var18).order(ByteOrder.LITTLE_ENDIAN);
               var1.position(var14 + 60);
               if (var1.read(var7) != var18) {
                  return false;
               }

               var7.rewind();

               try {
                  IUnit var8 = this.getUnitProcessor()
                     .process(var5, new BytesInput(Arrays.copyOf(var7.array(), var7.limit()), 0, var7.limit(), var5), this, null, false, true);
                  if (var8 != null) {
                     this.addChild(var8);
                  }
               } catch (Exception var10) {
                  gO.catching(var10);
                  this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("Problem during embedded file processing")));
               }

               var14 = var14 + 60 + var18;
               if (var14 % 2 != 0) {
                  var14++;
               }
            }
         }
      } catch (IOException var12) {
         Object[] var10000 = new Object[]{var12};
         return false;
      }
   }

   private String q(int var1) {
      String var2 = null;
      if (this.gP == 0) {
         return var2;
      } else {
         try (SeekableByteChannel var3 = this.getInput().getChannel()) {
            var3.position(this.gP + var1);
            int var4 = 1;
            ByteBuffer var5 = ByteBuffer.allocate(var4);
            var3.read(var5);
            var5.rewind();
            byte[] var6 = new byte[var5.remaining()];
            var5.get(var6);

            while (var6[var6.length - 1] != 0 && var6[var6.length - 1] != 10) {
               var5 = ByteBuffer.allocate(++var4);
               var3.position(this.gP + var1);
               var3.read(var5);
               var5.rewind();
               var6 = new byte[var5.remaining()];
               var5.get(var6);
            }

            var2 = Strings.decodeASCII(var6, 0, var4 - 1);
         } catch (IOException var9) {
            Object[] var10000 = new Object[]{var9};
         }

         return var2;
      }
   }

   CU q(SeekableByteChannel var1, int var2, String var3) throws IOException {
      ByteBuffer var4 = ByteBuffer.allocate(60).order(ByteOrder.LITTLE_ENDIAN);
      var1.position(var2);
      if (var1.read(var4) != 60) {
         return null;
      } else {
         var4.rewind();
         CU var5 = CU.q(var4);
         if (var3 != null) {
            String var6 = Strings.decodeASCII(var5.RF, 0, 16);
            if (!var6.equals(var3)) {
               return null;
            }
         }

         return var5;
      }
   }

   boolean q(SeekableByteChannel var1) throws IOException {
      ByteBuffer var2 = ByteBuffer.allocate((int)var1.size()).order(ByteOrder.BIG_ENDIAN);
      var1.position(68L);
      var1.read(var2);
      var2.rewind();
      this.RF = eo.q(var2);
      return this.RF.q() != 0;
   }

   boolean q(SeekableByteChannel var1, int var2) throws IOException {
      ByteBuffer var3 = ByteBuffer.allocate((int)var1.size()).order(ByteOrder.LITTLE_ENDIAN);
      var1.position(var2 + 60);
      var1.read(var3);
      var3.rewind();
      this.Dw = nI.q(var3);
      return this.Dw.q() != 0;
   }
}
