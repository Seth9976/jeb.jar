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
public class yt extends AbstractCodeObjectUnit {
   private static final ILogger sY = GlobalLog.getLogger(yt.class);
   public static String pC = ".code";
   public static long A = 0L;
   public static String kS = ".data";
   @SerId(1)
   byte[] wS;
   @SerId(2)
   HE UT;
   @SerId(3)
   ma E;

   public yt(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "eth", var1, var3, var4, var5);
   }

   public HE pC() {
      return this.UT;
   }

   @Override
   protected boolean processInternal() {
      try {
         boolean var31;
         try (InputStream var1 = this.getInput().getStream()) {
            this.wS = IO.readInputStream(var1);
            if (this.wS.length >= 10) {
               boolean var2 = true;
               int var3 = 0;

               for (int var4 = 0; var4 < this.wS.length - 10; var4++) {
                  int var5 = this.wS[var4] & 255;
                  if (var5 != 32 && var5 != 9 && var5 != 13 && var5 != 10) {
                     break;
                  }

                  var3++;
               }

               if (this.wS[var3] == 48 && this.wS[var3 + 1] == 120) {
                  var3 += 2;
               }

               for (int var18 = var3; var18 < var3 + 8; var18++) {
                  int var22 = this.wS[var18] & 255;
                  boolean var6 = var22 >= 48 && var22 <= 57 || var22 >= 97 && var22 <= 102 || var22 >= 65 && var22 <= 70;
                  if (!var6) {
                     var2 = false;
                     break;
                  }
               }

               if (!var2) {
                  if (this.wS.length >= 3 && this.wS[0] == 69 && this.wS[1] == 86 && this.wS[2] == 77) {
                     this.wS = Arrays.copyOfRange(this.wS, 3, this.wS.length);
                  }
               } else {
                  String var19 = Strings.decodeUTF8(this.wS);
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

                  this.wS = Formatter.hexStringToByteArray(var23.toString());
               }
            }

            this.UT = new HE(this);
            this.E = new ma(this.UT, this.wS);
            ma.Sv var14 = this.E.E();
            if (var14.pC) {
               if (var14.A == null) {
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
                     int var24 = var14.A;
                     int var28 = var14.kS == null ? this.wS.length : var24 + var14.kS;
                     this.wS = Arrays.copyOfRange(this.wS, var24, var28);
                     this.UT = new HE(this);
                     this.E = new ma(this.UT, this.wS);
                     this.E.E();
                  }
               }
            }

            this.E.sY();
            this.addSegment(new SegmentInformation(pC, 0L, this.wS.length, A, this.wS.length, 6));
            this.addSegment(new SegmentInformation(".data", 0L, 0L, 4294967296L, 65536L, 7));
            long var17 = (this.wS.length + 4095) / 4096 * 4096L;
            String var25 = "";
            if (this.E.eP != null) {
               var25 = var25 + Strings.ff(S.L("compiled with solc %s (metadata)"), this.E.eP);
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
               .setEntryPoint(A)
               .setNotes(var25)
               .build();
            this.setLoaderInformation(var29);

            try {
               String var30 = "evmbc";
               IUnit var33 = this.getUnitProcessor().process(var30 + "-image", this.getInput(), this, var30, true);
               if (var33 != null) {
                  this.addChild(var33);
               }
            } catch (Exception var11) {
               sY.catching(var11);
               this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("The contract image was not disassembled")));
            }

            var31 = true;
         }

         return var31;
      } catch (Exception var13) {
         sY.catching(var13);
         return false;
      }
   }

   public Integer pC(long var1) {
      return var1 >= A && var1 <= A + this.wS.length ? (int)(var1 - A) : null;
   }

   public Mh A(long var1) {
      return (Mh)this.E.UT.get((int)(var1 - A));
   }

   public eW kS(long var1) {
      int var3 = (int)(var1 - A);
      return (eW)this.E.gp.get(var3);
   }

   public nA A() {
      eW var1 = (eW)this.E.gp.get(0);
      return !(var1 instanceof nA) ? null : (nA)var1;
   }

   public List kS() {
      return this.E.oT;
   }

   public List pC(boolean var1) {
      List var2 = this.E.kS();
      if (!var1) {
         var2.remove(this.A());
      }

      return var2;
   }

   public List wS() {
      ArrayList var1 = new ArrayList();
      if (this.A() != null) {
         var1.add(this.A());
      }

      var1.addAll(this.kS());
      var1.addAll(this.pC(false));
      return var1;
   }

   public List UT() {
      return this.E.A();
   }

   public zp E() {
      return this.E.UT();
   }

   public Map sY() {
      return this.E.pC();
   }

   @Override
   protected IInput getMappableInput() {
      return new BytesInput(this.wS);
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
      Strings.ff(var1, S.L("Detected compiler: %s\n\n"), this.E());
      if (!this.sY().isEmpty()) {
         var1.append(S.L("Metadata"));
         var1.append(": ");
         var1.append(Formatter.toString(this.sY()));
         var1.append("\n\n");
      }

      List var2 = this.wS();
      int var3 = var2.size();
      Strings.ff(var1, S.L("%d routines detected\n"), var3);
      int var4 = 0;

      for (eW var6 : var2) {
         Strings.ff(var1, "%d/%d - %s\n", var4 + 1, var3, var6);
         var4++;
      }

      var1.append('\n');
      Strings.ff(var1, S.L("Orphan blocks: %s\n\n"), this.E.wS());
      return var1.toString();
   }
}
