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

public class sr {
   private static final ILogger Uv = GlobalLog.getLogger(sr.class);
   boolean q = true;
   private oH oW;
   private boolean gO;
   private boolean nf;
   int RF = 0;
   List xK = null;
   Map Dw = Collections.synchronizedMap(new HashMap());

   sr(oH var1) {
      this.oW = var1;
   }

   void q() {
      this.RF = 0;
      this.xK = null;
      this.Dw.clear();
   }

   public List RF() throws IOException, WI {
      Object var1 = null;
      String var2 = null;
      if (this.oW.q("qXfer:libraries-svr4:read")) {
         try {
            var2 = this.oW.q("libraries-svr4", "");
         } catch (WI var10) {
            if (this.oW.q("qXfer:libraries:read")) {
               try {
                  var2 = this.oW.q("libraries", "");
               } catch (WI var9) {
               }
            }
         }
      }

      if (var2 != null) {
         var1 = bg.q(var2);
      }

      if (var1 == null || var1.isEmpty()) {
         Xh var3 = this.oW.lm();
         if (var3 != null) {
            com.pnfsoftware.jeb.corei.debuggers.linux.nI var4 = var3.q();
            if (var4 != null) {
               var1 = new ArrayList();

               for (com.pnfsoftware.jeb.corei.debuggers.linux.CU var7 : var4.RF()) {
                  bg var8 = new bg(var7.q(), var7.RF());
                  var1.add(var8);
               }
            }
         }
      }

      if (var1 == null) {
         throw new JG("Cannot retrieve list of loaded library modules");
      } else {
         return (List)var1;
      }
   }

   public String xK() throws IOException, WI {
      String var1 = null;
      if (this.oW.q("qXfer:osdata:read")) {
         var1 = this.oW.q("osdata", "");
      }

      return var1;
   }

   public Yl Dw() throws IOException, WI {
      byte[] var1 = this.oW.Dw("?");
      return this.oW.q(var1);
   }

   public byte[] q(int var1, int var2) throws IOException, WI {
      Ht var3 = (Ht)this.Dw.get(var2);
      if (this.q && var3 != null) {
         return var3.getValue(var1);
      } else {
         String var4 = Strings.ff("p%x", var1);
         if (this.oW.io && var2 != 0) {
            var4 = var4 + Strings.ff(";thread:%x;", var2);
         }

         String var5 = this.oW.xK(var4);
         return MO.za(var5);
      }
   }

   public long q(int var1) throws IOException, WI {
      try {
         int var2 = this.oW.HF.getDescriptionEntryByName("pc").getNumber();
         byte[] var3 = this.q(var2, var1);
         return MO.q(var3, this.oW.gP());
      } catch (RuntimeException var4) {
         throw new WI(Strings.ff("PC register for thread %Xh was not determined", var1));
      }
   }

   public Ht Uv() throws IOException, WI {
      return this.RF(0);
   }

   public Ht RF(int var1) throws IOException, WI {
      if (var1 != 0) {
         this.Dw(var1);
      } else {
         try {
            var1 = this.gP();
         } catch (WI var9) {
            Uv.debug("Cannot retrieve the default thread - was a default thread set?");
         }
      }

      Ht var2 = (Ht)this.Dw.get(var1);
      if (this.q && var2 != null) {
         return var2;
      } else {
         kW var3 = null;
         if (!this.gO) {
            try {
               String var4 = this.oW.xK("g");
               Uv.trace("Raw registers block: %s", var4);
               byte[] var5 = this.RF(var4);
               var3 = this.oW.Me();
               if (!var3.q(var5, null)) {
                  throw new WI("Error processing registers block");
               }
            } catch (IW | JG var8) {
               this.gO = true;
            }
         }

         if (this.gO) {
            var3 = this.oW.Me();

            for (RegisterDescriptionEntry var12 : var3.q().getDescriptionEntries()) {
               int var6 = var12.getNumber();
               byte[] var7 = this.q(var6, var1);
               if (!var3.q(var6, var7)) {
                  throw new WI("Cannot read register number " + var6);
               }
            }
         }

         var2 = this.oW.za.q(var3);
         if (var2 == null) {
            throw new WI("Cannot parse registers data");
         } else {
            if (var1 != 0) {
               this.Dw.put(var1, var2);
            }

            return var2;
         }
      }
   }

   private byte[] RF(String var1) throws WI {
      if (var1.length() % 2 != 0) {
         throw new WI("Reading general registers malformed: not a multiple of 2.");
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
                  throw new WI("Malformed hex-encoded data");
               }
            }
         }

         return var3;
      }
   }

   public void q(int var1, byte[] var2, int var3) throws IOException, WI {
      String var4 = Strings.ff("P%x=%s", var1, MO.Dw(var2));
      if (this.oW.io && var3 != 0) {
         var4 = var4 + Strings.ff(";thread:%x;", var3);
      }

      String var5 = this.oW.xK(var4);
      if (!MO.RF(var5)) {
         throw new WI("Cannot write register #" + var1);
      } else {
         this.Dw.remove(var3);
      }
   }

   public void q(Ht var1) throws IOException, WI {
      this.q(var1, 0);
   }

   public void q(Ht var1, int var2) throws IOException, WI {
      if (var2 != 0) {
         this.Dw(var2);
      } else {
         try {
            var2 = this.gP();
         } catch (WI var8) {
            Uv.debug("Cannot retrieve the default thread; will attempt registers write anyway");
         }
      }

      if (!this.nf) {
         byte[] var3 = var1.RF();
         if (var3 == null) {
            throw new WI("Cannot build registers data");
         }

         String var4 = MO.Dw(var3);

         try {
            String var5 = this.oW.xK("G" + var4);
            if (!MO.RF(var5)) {
               throw new WI("Cannot write registers");
            }
         } catch (JG var7) {
            this.nf = true;
         }
      }

      if (this.nf) {
         for (Entry var10 : var1.q(true).entrySet()) {
            int var11 = (Integer)var10.getKey();
            byte[] var6 = (byte[])var10.getValue();
            this.q(var11, var6, var2);
         }
      }

      this.Dw.remove(var2);
   }

   public boolean q(boolean var1) throws IOException, WI {
      Ht var2 = this.Uv();
      long var3 = var2.getProgramCounter();
      this.oW.gP.xK(var3);
      if (!var1) {
         IInstruction var5 = this.oW.za.RF(var2);
         if (var5 == null) {
            throw new WI(Strings.ff("Cannot step over from PC=%Xh: the instruction could not be parsed", var2.getProgramCounter()));
         }

         IFlowInformation var6 = var5.getRoutineCall(var3);
         if (var6.isBroken()) {
            long var7 = var3 + var5.getSize();
            int var9 = var6.getDelaySlotCount();

            while (var9-- > 0) {
               IInstruction var10 = this.oW.za.RF(var7);
               var7 += var10.getSize();
            }

            if (!this.oW.za.Dw(var7)) {
               throw new WI(Strings.ff("Unable to set breakpoint at address %Xh", var7));
            }

            return this.oW();
         }
      }

      if (this.oW.xK.xK) {
         int var11 = this.gP();
         String var12 = "vCont;s";
         if (this.oW.io && var11 != 0) {
            var12 = var12 + Strings.ff(":%x", var11);
         }

         this.oW.q(var12, false);
         return true;
      } else if (this.oW.za.q(true)) {
         return this.oW();
      } else {
         throw new WI(Strings.ff("Step action on processor %s is not supported", this.oW.nf()));
      }
   }

   public boolean oW() throws IOException {
      if (this.oW.If()) {
         return false;
      } else {
         try {
            if (this.oW.gO() == u.RF) {
               Ht var1 = this.Uv();
               if (this.oW.za.q()) {
                  this.oW.za.q(var1, true);
               }

               long var2 = var1.getProgramCounter();

               for (fQ var5 : this.oW.gP.q()) {
                  if (var5.q() != var2) {
                     this.oW.gP.q(var5, true);
                  }
               }
            } else if (this.oW.gO() == u.xK) {
               try {
                  Ht var9 = this.Uv();
                  if (this.oW.za.q()) {
                     this.oW.za.q(var9, true);
                  }
               } catch (WI var7) {
                  Uv.error("Cannot read registers; trapping may not have been disabled. Error: %s", var7);
               }

               HashSet var10 = new HashSet();

               for (pr var3 : this.za()) {
                  try {
                     long var14 = this.q(var3.q());
                     var10.add(var14);
                  } catch (WI var6) {
                     Uv.error(var6.toString());
                  }
               }

               for (fQ var13 : this.oW.gP.q()) {
                  if (!var10.contains(var13.q())) {
                     this.oW.gP.q(var13, true);
                  }
               }
            }
         } catch (WI var8) {
            Uv.error("Cannot read registers; trapping may not have been disabled. Error: %s", var8);
         }

         this.gO();
         return true;
      }
   }

   public void gO() throws IOException {
      String var1 = Strings.ff("vCont;c");
      this.oW.q(var1, this.oW.zz());
   }

   public void xK(int var1) throws IOException {
      String var2 = Strings.ff("vCont;c:%x", var1);
      this.oW.q(var2, this.oW.zz());
   }

   public void nf() throws IOException {
      String var1 = Strings.ff("vCont;t");
      this.oW.q(var1, false);
   }

   public void q(boolean var1, int var2, int var3) throws IOException, WI {
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

         boolean var5 = MO.RF(this.oW.xK(var4));
         if (!var5) {
            throw new WI(Strings.ff("Cannot set default thread to %Xh", var2));
         } else {
            this.RF = var2;
         }
      }
   }

   public void q(boolean var1, int var2) throws IOException, WI {
      this.q(var1, var2, Integer.MIN_VALUE);
   }

   public void Dw(int var1) throws IOException, WI {
      this.q(false, var1);
      this.q(true, var1);
   }

   public int gP() throws IOException, WI {
      if (this.RF != 0) {
         return this.RF;
      } else {
         String var1 = this.oW.xK("qC");
         if (!var1.startsWith("QC")) {
            throw new WI("Unknown return string");
         } else {
            var1 = var1.substring(2);
            int[] var2 = MO.lm(var1);
            this.RF = var2[1];
            return var2[1];
         }
      }
   }

   public boolean Uv(int var1) throws IOException, WI {
      String var2 = Strings.ff("T%x", var1);
      String var3 = this.oW.xK(var2);
      return MO.RF(var3);
   }

   public List za() throws IOException, WI {
      if (this.q && this.xK != null) {
         return this.xK;
      } else {
         Object var1 = null;
         if (this.oW.gO() == u.xK) {
            try {
               String var2 = this.oW.xK("jThreadsInfo");
               var1 = new bz(this.oW).RF(var2);
            } catch (WI var10) {
            }
         }

         if (var1 == null) {
            String var11 = null;
            if (this.oW.q("qXfer:threads:read")) {
               var11 = this.oW.q("threads", "");
            }

            if (var11 != null) {
               var1 = new bz(this.oW).q(var11);
            }
         }

         if (var1 == null) {
            var1 = new ArrayList();
            byte[] var12 = this.oW.Dw("qfThreadInfo");

            while (true) {
               String var3 = Strings.decodeASCII(var12);
               if (var3.startsWith("m")) {
                  for (String var7 : var3.substring(1).split(",")) {
                     int[] var8 = MO.lm(var7);
                     if (var8 != null) {
                        int var9 = var8[1];
                        var1.add(new pr(var9, null));
                     }
                  }
               } else if (var3.startsWith("l")) {
                  break;
               }

               var12 = this.oW.Dw("qsThreadInfo");
            }
         }

         this.xK = (List)var1;
         return (List)var1;
      }
   }

   public String q(String var1) throws IOException, WI {
      String var2 = "qRcmd," + MO.Dw(Strings.encodeASCII(var1));
      return this.oW.xK(var2);
   }
}
