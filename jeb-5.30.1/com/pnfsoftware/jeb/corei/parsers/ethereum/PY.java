package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.events.ClientNotification;
import com.pnfsoftware.jeb.core.events.ClientNotificationLevel;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.QuestionNotificationYesNo;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.codeobject.AbstractCodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ILinkInfoProvider;
import com.pnfsoftware.jeb.core.units.codeobject.LoaderInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SegmentInformation;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Ser
public class PY extends AbstractCodeObjectUnit {
   private static final ILogger nf = GlobalLog.getLogger(PY.class);
   public static String q = ".code";
   public static long RF = 0L;
   public static String xK = ".data";
   public static final long Dw = 4294967296L;
   @SerId(1)
   byte[] Uv;
   @SerId(2)
   Bu oW;
   @SerId(3)
   eM gO;

   public PY(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "eth", var1, var3, var4, var5);
   }

   public Bu q() {
      return this.oW;
   }

   @Override
   protected boolean processInternal() {
      try {
         boolean var31;
         try (InputStream var1 = this.getInput().getStream()) {
            this.Uv = IO.readInputStream(var1);
            if (this.Uv.length >= 10) {
               boolean var2 = true;
               int var3 = 0;

               for (int var4 = 0; var4 < this.Uv.length - 10; var4++) {
                  int var5 = this.Uv[var4] & 255;
                  if (var5 != 32 && var5 != 9 && var5 != 13 && var5 != 10) {
                     break;
                  }

                  var3++;
               }

               if (this.Uv[var3] == 48 && this.Uv[var3 + 1] == 120) {
                  var3 += 2;
               }

               for (int var18 = var3; var18 < var3 + 8; var18++) {
                  int var22 = this.Uv[var18] & 255;
                  boolean var6 = var22 >= 48 && var22 <= 57 || var22 >= 97 && var22 <= 102 || var22 >= 65 && var22 <= 70;
                  if (!var6) {
                     var2 = false;
                     break;
                  }
               }

               if (!var2) {
                  if (this.Uv.length >= 3 && this.Uv[0] == 69 && this.Uv[1] == 86 && this.Uv[2] == 77) {
                     this.Uv = Arrays.copyOfRange(this.Uv, 3, this.Uv.length);
                  }
               } else {
                  String var19 = Strings.decodeUTF8(this.Uv);
                  StringBuilder var23 = new StringBuilder();

                  for (String var9 : Strings.splitLines(var19)) {
                     var9 = Strings.trim(var9);
                     if (!var9.isEmpty() && !Strings.startsWith(var9, "#", ";", "//")) {
                        if (var9.startsWith("0x")) {
                           var9 = var9.substring(2);
                        }

                        var23.append(var9);
                     }
                  }

                  this.Uv = Formatter.hexStringToByteArray(var23.toString());
               }
            }

            this.oW = new Bu(this);
            this.gO = new eM(this.oW, this.Uv);
            eM.CU var14 = this.gO.Me();
            if (var14.q) {
               if (var14.RF == null) {
                  String var15 = S.L(
                     "This code looks like contract creation code.\n\nJEB may not be able to process this contract properly, since the actual runtime-code is located in the data area.\n\nWe recommend you extract the runtime-code manually and re-process the file."
                  );
                  ClientNotification var20 = new ClientNotification(var15, ClientNotificationLevel.WARNING);
                  this.notifyListeners(new JebEvent(J.Notification, var20));
               } else {
                  String var16 = S.L(
                     "This code looks like contract creation code.\n\nJEB may not be able to process this contract properly, since the actual runtime-code is located in the data area.\n\nPress YES to let JEB extract the runtime-code; press NO to proceed and parse the current code as it is."
                  );
                  QuestionNotificationYesNo var21 = new QuestionNotificationYesNo(var16, true);
                  this.notifyListeners(new JebEvent(J.Notification, var21));
                  if (Boolean.TRUE.equals(var21.getResponse())) {
                     int var24 = var14.RF;
                     int var28 = var14.xK == null ? this.Uv.length : var24 + var14.xK;
                     this.Uv = Arrays.copyOfRange(this.Uv, var24, var28);
                     this.oW = new Bu(this);
                     this.gO = new eM(this.oW, this.Uv);
                     this.gO.Me();
                  }
               }
            }

            this.gO.PV();
            this.addSegment(new SegmentInformation(q, 0L, this.Uv.length, RF, this.Uv.length, 6));
            this.addSegment(new SegmentInformation(".data", 0L, 0L, 4294967296L, 65536L, 7));
            long var17 = (this.Uv.length + 4095) / 4096 * 4096L;
            String var25 = "";
            if (this.gO.PV != null) {
               var25 = var25 + Strings.ff(S.L("compiled with solc %s (metadata)"), this.gO.PV);
               var25 = var25 + " ";
            }

            LoaderInformation var29 = new LoaderInformation.Builder()
               .setFlags(16)
               .setVersion("1")
               .setTargetProcessor(ProcessorType.UNKNOWN)
               .setWordSize(256)
               .setEndianness(Endianness.BIG_ENDIAN)
               .setImageBase(0L)
               .setImageSize(var17)
               .setEntryPoint(RF)
               .setNotes(var25)
               .build();
            this.setLoaderInformation(var29);

            try {
               String var30 = "evmbc";
               IUnit var33 = this.getUnitProcessor().process(var30 + " image", this.getInput(), this, var30, true);
               if (var33 != null) {
                  this.addChild(var33);
               }
            } catch (Exception var11) {
               nf.catching(var11);
               this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("The contract image was not disassembled")));
            }

            var31 = true;
         }

         return var31;
      } catch (Exception var13) {
         nf.catching(var13);
         return false;
      }
   }

   public Integer q(long var1) {
      return var1 >= RF && var1 <= RF + this.Uv.length ? (int)(var1 - RF) : null;
   }

   public Long q(int var1) {
      return var1 >= 0 && var1 <= this.Uv.length ? RF + var1 : null;
   }

   public vX RF(long var1) {
      return (vX)this.gO.Uv.get((int)(var1 - RF));
   }

   public List RF() {
      return new ArrayList(this.gO.za.values());
   }

   public qx xK(long var1) {
      int var3 = (int)(var1 - RF);
      return (qx)this.gO.za.get(var3);
   }

   public Fw xK() {
      qx var1 = (qx)this.gO.za.get(0);
      return !(var1 instanceof Fw) ? null : (Fw)var1;
   }

   public List Dw() {
      return this.gO.lm;
   }

   public List Uv() {
      return this.gO.zz();
   }

   public List q(boolean var1) {
      List var2 = this.gO.zz();
      if (!var1) {
         var2.remove(this.xK());
      }

      return var2;
   }

   public List oW() {
      ArrayList var1 = new ArrayList();
      if (this.xK() != null) {
         var1.add(this.xK());
      }

      var1.addAll(this.Dw());
      var1.addAll(this.q(false));
      return var1;
   }

   public List gO() {
      return this.gO.Uv();
   }

   public qV nf() {
      return this.gO.qa();
   }

   public Map gP() {
      return this.gO.Dw();
   }

   @Override
   protected IInput getMappableInput() {
      return new BytesInput(this.Uv);
   }

   @Override
   protected boolean shouldAllocateFullImage() {
      return false;
   }

   @Override
   protected boolean applyRelocations(IVirtualMemory var1, long var2, ILinkInfoProvider var4) {
      return false;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append('\n');
      Strings.ff(var1, S.L("Detected compiler: %s\n\n"), this.nf());
      if (!this.gP().isEmpty()) {
         var1.append(S.L("Metadata"));
         var1.append(": ");
         var1.append(Formatter.toString(this.gP()));
         var1.append("\n\n");
      }

      List var2 = this.oW();
      int var3 = var2.size();
      Strings.ff(var1, S.L("%d routines detected\n"), var3);
      int var4 = 0;

      for (qx var6 : var2) {
         Strings.ff(var1, "%d/%d - %s\n", var4 + 1, var3, var6);
         var4++;
      }

      var1.append('\n');
      Strings.ff(var1, S.L("Orphan blocks: %s\n\n"), this.gO.HF());
      return var1.toString();
   }
}
