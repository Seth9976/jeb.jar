package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class qa {
   private static final ILogger UT = GlobalLog.getLogger(qa.class);
   boolean pC = true;
   private aI E;
   private boolean sY;
   private boolean ys;
   int A = 0;
   List kS = null;
   Map wS = Collections.synchronizedMap(new HashMap());

   qa(aI var1) {
      this.E = var1;
   }

   void pC() {
      this.A = 0;
      this.kS = null;
      this.wS.clear();
   }

   public List A() throws IOException, Ae {
      Object var1 = null;
      String var2 = null;
      if (this.E.A("qXfer:libraries-svr4:read")) {
         try {
            var2 = this.E.pC("libraries-svr4", "");
         } catch (Ae var10) {
            if (this.E.A("qXfer:libraries:read")) {
               try {
                  var2 = this.E.pC("libraries", "");
               } catch (Ae var9) {
               }
            }
         }
      }

      if (var2 != null) {
         var1 = JE.pC(var2);
      }

      if (var1 == null || var1.isEmpty()) {
         Gg var3 = this.E.ld();
         if (var3 != null) {
            com.pnfsoftware.jeb.corei.debuggers.linux.K var4 = var3.pC();
            if (var4 != null) {
               var1 = new ArrayList();

               for (com.pnfsoftware.jeb.corei.debuggers.linux.Sv var7 : var4.A()) {
                  JE var8 = new JE(var7.pC(), var7.A());
                  var1.add(var8);
               }
            }
         }
      }

      if (var1 == null) {
         throw new RD("Cannot retrieve list of loaded library modules");
      } else {
         return (List)var1;
      }
   }

   public String kS() throws IOException, Ae {
      String var1 = null;
      if (this.E.A("qXfer:osdata:read")) {
         var1 = this.E.pC("osdata", "");
      }

      return var1;
   }

   public Ez wS() throws IOException, Ae {
      byte[] var1 = this.E.wS("?");
      return this.E.pC(var1);
   }

   public byte[] pC(int var1, int var2) throws IOException, Ae {
      LD var3 = (LD)this.wS.get(var2);
      if (this.pC && var3 != null) {
         return var3.getValue(var1);
      } else {
         String var4 = Strings.ff("p%x", var1);
         if (this.E.xC && var2 != 0) {
            var4 = var4 + Strings.ff(";thread:%x;", var2);
         }

         String var5 = this.E.kS(var4);
         return zI.ld(var5);
      }
   }

   public long pC(int var1) throws IOException, Ae {
      try {
         int var2 = this.E.NS.getDescriptionEntryByName("pc").getNumber();
         byte[] var3 = this.pC(var2, var1);
         return zI.pC(var3, this.E.ys());
      } catch (RuntimeException var4) {
         throw new Ae(Strings.ff("PC register for thread %Xh was not determined", var1));
      }
   }

   public LD UT() throws IOException, Ae {
      return this.A(0);
   }

   public LD A(int var1) throws IOException, Ae {
      if (var1 != 0) {
         this.wS(var1);
      } else {
         try {
            var1 = this.ld();
         } catch (Ae var9) {
            UT.debug("Cannot retrieve the default thread - was a default thread set?");
         }
      }

      LD var2 = (LD)this.wS.get(var1);
      if (this.pC && var2 != null) {
         return var2;
      } else {
         Tl var3 = null;
         if (!this.sY) {
            try {
               String var4 = this.E.kS("g");
               UT.trace("Raw registers block: %s", var4);
               byte[] var5 = this.A(var4);
               var3 = this.E.Sc();
               if (!var3.pC(var5, null)) {
                  throw new Ae("Error processing registers block");
               }
            } catch (BD | RD var8) {
               this.sY = true;
            }
         }

         if (this.sY) {
            var3 = this.E.Sc();

            for (RegisterDescriptionEntry var12 : var3.pC().getDescriptionEntries()) {
               int var6 = var12.getNumber();
               byte[] var7 = this.pC(var6, var1);
               if (!var3.pC(var6, var7)) {
                  throw new Ae("Cannot read register number " + var6);
               }
            }
         }

         var2 = this.E.gp.pC(var3);
         if (var2 == null) {
            throw new Ae("Cannot parse registers data");
         } else {
            if (var1 != 0) {
               this.wS.put(var1, var2);
            }

            return var2;
         }
      }
   }

   private byte[] A(String var1) throws Ae {
      if (var1.length() % 2 != 0) {
         throw new Ae("Reading general registers malformed: not a multiple of 2.");
      } else {
         int var2 = var1.length() / 2;
         byte[] var3 = new byte[var2];

         for (int var4 = 0; var4 < var2; var4++) {
            char var5 = var1.charAt(var4 * 2);
            char var6 = var1.charAt(var4 * 2 + 1);
            if (var5 == 'x' && var6 == 'x') {
               var3[var4] = 0;
            } else {
               try {
                  int var7 = Integer.parseInt("" + var5 + var6, 16);
                  var3[var4] = (byte)var7;
               } catch (NumberFormatException var8) {
                  throw new Ae("Malformed hex-encoded data");
               }
            }
         }

         return var3;
      }
   }

   public void pC(int var1, byte[] var2, int var3) throws IOException, Ae {
      String var4 = Strings.ff("P%x=%s", var1, zI.kS(var2));
      if (this.E.xC && var3 != 0) {
         var4 = var4 + Strings.ff(";thread:%x;", var3);
      }

      String var5 = this.E.kS(var4);
      if (!zI.A(var5)) {
         throw new Ae("Cannot write register #" + var1);
      } else {
         this.wS.remove(var3);
      }
   }

   public void pC(LD var1) throws IOException, Ae {
      this.pC(var1, 0);
   }

   public void pC(LD var1, int var2) throws IOException, Ae {
      if (var2 != 0) {
         this.wS(var2);
      } else {
         try {
            var2 = this.ld();
         } catch (Ae var8) {
            UT.debug("Cannot retrieve the default thread; will attempt registers write anyway");
         }
      }

      if (!this.ys) {
         byte[] var3 = var1.A();
         if (var3 == null) {
            throw new Ae("Cannot build registers data");
         }

         String var4 = zI.kS(var3);

         try {
            String var5 = this.E.kS("G" + var4);
            if (!zI.A(var5)) {
               throw new Ae("Cannot write registers");
            }
         } catch (RD var7) {
            this.ys = true;
         }
      }

      if (this.ys) {
         for (Entry var10 : var1.pC(true).entrySet()) {
            int var11 = (Integer)var10.getKey();
            byte[] var6 = (byte[])var10.getValue();
            this.pC(var11, var6, var2);
         }
      }

      this.wS.remove(var2);
   }

   public boolean pC(boolean var1) throws IOException, Ae {
      LD var2 = this.UT();
      long var3 = var2.getProgramCounter();
      this.E.ld.A(var3);
      if (!var1) {
         IInstruction var5 = this.E.gp.A(var2);
         if (var5 == null) {
            throw new Ae(Strings.ff("Cannot step over from PC=%Xh: the instruction could not be parsed", var2.getProgramCounter()));
         }

         IFlowInformation var6 = var5.getRoutineCall(var3);
         if (var6.isBroken()) {
            long var7 = var3 + var5.getSize();
            int var9 = var6.getDelaySlotCount();

            while (var9-- > 0) {
               IInstruction var10 = this.E.gp.A(var7);
               var7 += var10.getSize();
            }

            if (!this.E.gp.kS(var7)) {
               throw new Ae(Strings.ff("Unable to set breakpoint at address %Xh", var7));
            }

            return this.E();
         }
      }

      if (this.E.kS.kS) {
         int var11 = this.ld();
         String var12 = "vCont;s";
         if (this.E.xC && var11 != 0) {
            var12 = var12 + Strings.ff(":%x", var11);
         }

         this.E.pC(var12, false);
         return true;
      } else if (this.E.gp.pC(true)) {
         return this.E();
      } else {
         throw new Ae(Strings.ff("Step action on processor %s is not supported", this.E.sY()));
      }
   }

   public boolean E() throws IOException {
      if (this.E.Aj()) {
         return false;
      } else {
         try {
            if (this.E.E() == jT.A) {
               LD var1 = this.UT();
               if (this.E.gp.pC()) {
                  this.E.gp.pC(var1, true);
               }

               long var2 = var1.getProgramCounter();

               for (xK var5 : this.E.ld.pC()) {
                  if (var5.pC() != var2) {
                     this.E.ld.pC(var5, true);
                  }
               }
            } else if (this.E.E() == jT.kS) {
               try {
                  LD var9 = this.UT();
                  if (this.E.gp.pC()) {
                     this.E.gp.pC(var9, true);
                  }
               } catch (Ae var7) {
                  UT.error("Cannot read registers; trapping may not have been disabled. Error: %s", var7);
               }

               HashSet var10 = new HashSet();

               for (Dn var3 : this.gp()) {
                  try {
                     long var14 = this.pC(var3.pC());
                     var10.add(var14);
                  } catch (Ae var6) {
                     UT.error(var6.toString());
                  }
               }

               for (xK var13 : this.E.ld.pC()) {
                  if (!var10.contains(var13.pC())) {
                     this.E.ld.pC(var13, true);
                  }
               }
            }
         } catch (Ae var8) {
            UT.error("Cannot read registers; trapping may not have been disabled. Error: %s", var8);
         }

         this.sY();
         return true;
      }
   }

   public void sY() throws IOException {
      String var1 = Strings.ff("vCont;c");
      this.E.pC(var1, this.E.oT());
   }

   public void kS(int var1) throws IOException {
      String var2 = Strings.ff("vCont;c:%x", var1);
      this.E.pC(var2, this.E.oT());
   }

   public void ys() throws IOException {
      String var1 = Strings.ff("vCont;t");
      this.E.pC(var1, false);
   }

   public void pC(boolean var1, int var2, int var3) throws IOException, Ae {
      if (var2 < -1) {
         throw new IllegalArgumentException();
      } else if (var3 < -1 && var3 != Integer.MIN_VALUE) {
         throw new IllegalArgumentException();
      } else if (var3 == -1 && var2 > 0) {
         throw new IllegalArgumentException();
      } else {
         String var4;
         if (var3 == Integer.MIN_VALUE) {
            var4 = Strings.ff("H%c%x", Character.valueOf((char)(var1 ? 'c' : 'g')), var2);
         } else {
            var4 = Strings.ff("H%cp%x:%x", Character.valueOf((char)(var1 ? 'c' : 'g')), var3, var2);
         }

         boolean var5 = zI.A(this.E.kS(var4));
         if (!var5) {
            throw new Ae(Strings.ff("Cannot set default thread to %Xh", var2));
         } else {
            this.A = var2;
         }
      }
   }

   public void pC(boolean var1, int var2) throws IOException, Ae {
      this.pC(var1, var2, Integer.MIN_VALUE);
   }

   public void wS(int var1) throws IOException, Ae {
      this.pC(false, var1);
      this.pC(true, var1);
   }

   public int ld() throws IOException, Ae {
      if (this.A != 0) {
         return this.A;
      } else {
         String var1 = this.E.kS("qC");
         if (!var1.startsWith("QC")) {
            throw new Ae("Unknown return string");
         } else {
            var1 = var1.substring(2);
            int[] var2 = zI.gp(var1);
            this.A = var2[1];
            return var2[1];
         }
      }
   }

   public List gp() throws IOException, Ae {
      if (this.pC && this.kS != null) {
         return this.kS;
      } else {
         Object var1 = null;
         if (this.E.E() == jT.kS) {
            try {
               String var2 = this.E.kS("jThreadsInfo");
               var1 = new cR(this.E).A(var2);
            } catch (Ae var10) {
            }
         }

         if (var1 == null) {
            String var11 = null;
            if (this.E.A("qXfer:threads:read")) {
               var11 = this.E.pC("threads", "");
            }

            if (var11 != null) {
               var1 = new cR(this.E).pC(var11);
            }
         }

         if (var1 == null) {
            var1 = new ArrayList();
            byte[] var12 = this.E.wS("qfThreadInfo");

            while (true) {
               String var3 = Strings.decodeASCII(var12);
               if (var3.startsWith("m")) {
                  for (String var7 : var3.substring(1).split(",")) {
                     int[] var8 = zI.gp(var7);
                     if (var8 != null) {
                        int var9 = var8[1];
                        var1.add(new Dn(var9, null));
                     }
                  }
               } else if (var3.startsWith("l")) {
                  break;
               }

               var12 = this.E.wS("qsThreadInfo");
            }
         }

         this.kS = (List)var1;
         return (List)var1;
      }
   }

   public String pC(String var1) throws IOException, Ae {
      String var2 = "qRcmd," + zI.kS(Strings.encodeASCII(var1));
      return this.E.kS(var2);
   }
}
