package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerThreadStatus;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Q {
   private static final ILogger q = GlobalLog.getLogger(Q.class);
   private Ux RF;
   private MultiMap xK;
   private Map Dw = new HashMap();
   private Map Uv = new HashMap();
   private Map oW = new HashMap();
   private aB gO;
   private aB nf;
   private aB gP;
   private aB za;

   Q(Ux var1) {
      this.RF = var1;
   }

   public oG q(Nv var1, long var2) throws IOException, zy {
      byte var4;
      long var5;
      long var7;
      switch (var1.Uv) {
         case 0:
            return null;
         case 1:
            var4 = -2;
            var5 = this.RF(var2, var1.gO);
            var7 = 0L;
            break;
         case 2:
            var4 = -3;
            var5 = this.q(var2, var1.gO);
            var7 = 0L;
            break;
         case 3:
            var4 = 1;
            var5 = this.q(var2, var1.gO);
            var7 = var1.nf;
            break;
         default:
            return null;
      }

      return var5 == -1L ? null : new oG(var4, var2, var5, var7);
   }

   public List q(Nv var1) throws IOException, zy {
      ArrayList var2 = new ArrayList();

      for (long var4 : this.xK(var1.oW)) {
         oG var6 = this.q(var1, var4);
         if (var6 != null) {
            var2.add(var6);
         }
      }

      return var2;
   }

   private String Uv(String var1) {
      if (var1.equalsIgnoreCase("string")) {
         return "Ljava/lang/String;";
      } else {
         if (var1.length() == 1) {
            switch (var1.charAt(0)) {
               case 'B':
                  return "Ljava/lang/Byte;";
               case 'C':
                  return "Ljava/lang/Character;";
               case 'D':
                  return "Ljava/lang/Double;";
               case 'E':
               case 'G':
               case 'H':
               case 'K':
               case 'L':
               case 'M':
               case 'N':
               case 'O':
               case 'P':
               case 'Q':
               case 'R':
               case 'T':
               case 'U':
               case 'V':
               case 'W':
               case 'X':
               case 'Y':
               default:
                  break;
               case 'F':
                  return "Ljava/lang/Float;";
               case 'I':
                  return "Ljava/lang/Integer;";
               case 'J':
                  return "Ljava/lang/Long;";
               case 'S':
                  return "Ljava/lang/Short;";
               case 'Z':
                  return "Ljava/lang/Boolean;";
            }
         }

         return var1;
      }
   }

   private void Uv(boolean var1) throws IOException, zy {
      if (var1) {
         this.xK = null;
      }

      if (this.xK == null) {
         this.xK = new MultiMap();

         try {
            Gl var2 = this.RF.Dw.za();

            for (hL var6 : var2.q) {
               this.xK.put(var6.xK, var6);
            }
         } catch (Fx var7) {
            throw new zy(var7, "Cannot retrieve types");
         }
      }
   }

   public Collection q(boolean var1) throws IOException, zy {
      this.Uv(var1);
      return this.xK.values();
   }

   public List q(String var1) throws IOException, zy {
      boolean var2 = this.xK != null;
      this.Uv(false);
      var1 = this.Uv(var1);
      List var3 = this.xK.get(var1);
      if (var3 == null) {
         ArrayList var4 = new ArrayList();

         for (String var6 : this.xK.keySet()) {
            if (var6.contains(var1)) {
               var4.add(var6);
            }
         }

         if (var4.isEmpty()) {
            if (var2) {
               this.xK = null;
               return this.q(var1);
            }

            return null;
         }

         if (var4.size() >= 2) {
            throw new zy("Ambiguous type for \"%s\" (%d candidates)", var1, var4.size());
         }

         var3 = this.xK.get((String)var4.get(0));
      }

      return var3;
   }

   public List q(long var1, String var3, String var4) throws IOException, zy {
      wM var5;
      try {
         var5 = this.RF.Dw.Uv(var1);
      } catch (Fx var11) {
         throw new zy(var11, "Error while trying to retrieve methods of class %d", var1);
      }

      if (Strings.isBlank(var3) && Strings.isBlank(var4)) {
         return Arrays.asList(var5.q);
      } else {
         ArrayList var6 = new ArrayList();

         for (ih var10 : var5.q) {
            if (var10.HF.equals(var3) || var3 == null) {
               var6.add(var10);
            }
         }

         if (var6.isEmpty()) {
            for (ih var19 : var5.q) {
               if (var19.HF.contains(var3)) {
                  var6.add(var19);
               }
            }
         }

         if (var6.size() >= 1 && var4 != null && !"()".equals(var4)) {
            ArrayList var13 = new ArrayList();

            for (ih var18 : var6) {
               if (var18.LK.equals(var4)) {
                  var13.add(var18);
               }
            }

            if (var13.isEmpty()) {
               StringBuilder var16 = new StringBuilder();
               var6.forEach(var1x -> var16.append(var1x.LK).append(", "));
               var16.delete(var16.length() - 2, var16.length());
               q.info(S.L("No matching signature found. Available signatures are: %s"), var16);
            }

            var6 = var13;
         }

         return var6;
      }
   }

   private ih RF(long var1, long var3) throws IOException, zy {
      for (ih var7 : this.q(var1, null, null)) {
         if (var7.JY == var3) {
            return var7;
         }
      }

      return null;
   }

   public ih RF(long var1, String var3, String var4) throws IOException, zy {
      List var5 = this.q(var1, var3, var4);
      return var5.isEmpty() ? null : (ih)var5.get(0);
   }

   public hw[] q(long var1) throws IOException, zy {
      synchronized (this.Dw) {
         hw[] var4 = (hw[])this.Dw.get(var1);
         if (var4 == null) {
            try {
               var4 = this.RF.Dw.Dw(var1).q;
               this.Dw.put(var1, var4);
            } catch (Fx var7) {
               throw new zy(var7, "Error while trying to retrieve fields of class %d", var1);
            }
         }

         return var4;
      }
   }

   public hw xK(long var1, String var3, String var4) throws IOException, zy {
      hw[] var5 = this.q(var1);
      ArrayList var6 = new ArrayList();

      for (hw var10 : var5) {
         if (var10.lm.equals(var3)) {
            var6.add(var10);
         }
      }

      if (var6.isEmpty()) {
         for (hw var17 : var5) {
            if (var17.lm.contains(var3)) {
               var6.add(var17);
            }
         }
      }

      if (var6.size() >= 1 && var4 != null) {
         ArrayList var12 = new ArrayList();

         for (hw var16 : var6) {
            if (var16.zz.equals(var4)) {
               var12.add(var16);
            }
         }

         var6 = var12;
      }

      return var6.isEmpty() ? null : (hw)var6.get(0);
   }

   public boolean RF(long var1) throws IOException, Fx {
      try {
         TG var3 = this.RF.Dw.JY(var1);
         return var3.q != jw.q.q();
      } catch (Fx var4) {
         if (var4.RF() != FZ.RF.q()) {
            throw var4;
         } else {
            return false;
         }
      }
   }

   public boolean xK(long var1) throws IOException, Fx {
      TG var3 = this.RF.Dw.JY(var1);
      return var3.RF == 1;
   }

   public String Dw(long var1) throws IOException, Fx {
      String var3 = this.RF.Dw.za(var1);
      TG var4 = this.RF.Dw.JY(var1);
      return Strings.ff("%d: \"%s\" (%s)%s", var1, var3, jw.q(var4.q), var4.RF == 1 ? " [suspended]" : "");
   }

   public long RF(String var1) throws IOException, Fx {
      long var2 = Conversion.stringToLong(var1);
      if (var2 != 0L && this.RF(var2)) {
         return var2;
      } else {
         Uc var4 = this.RF.Dw.RF();
         ArrayList var5 = new ArrayList();

         for (long var9 : var4.RF) {
            String var11 = this.RF.Dw.za(var9);
            if (var11.equals(var1)) {
               return var9;
            }

            if (var11.contains(var1)) {
               var5.add(var9);
            }
         }

         return var5.size() != 1 ? 0L : (Long)var5.get(0);
      }
   }

   public Vi Uv(long var1) throws IOException, zy {
      synchronized (this.Uv) {
         Vi var4 = (Vi)this.Uv.get(var1);
         if (var4 == null) {
            try {
               var4 = this.RF.Dw.xK(var1);
            } catch (Fx var7) {
               q.catching(var7);
               throw new zy(var7, "Error while trying to retrieve class %d", var1);
            }

            this.Uv.put(var1, var4);
         }

         return var4;
      }
   }

   public String oW(long var1) throws IOException, zy {
      return this.Uv(var1).q;
   }

   public List xK(String var1) throws IOException, zy {
      ArrayList var2 = new ArrayList();
      long var3 = Conversion.stringToLong(var1);
      if (var3 != 0L) {
         var2.add(var3);
      } else {
         List var5 = this.q(var1);
         if (var5 != null) {
            for (hL var7 : var5) {
               var2.add(var7.RF);
            }
         }
      }

      return var2;
   }

   public long Dw(String var1) throws IOException, zy {
      List var2 = this.xK(var1);
      return var2.isEmpty() ? -1L : (Long)var2.get(0);
   }

   public String q(long var1, long var3) throws IOException, zy {
      for (ih var7 : this.q(var1, null, null)) {
         if (var7.JY == var3) {
            return Strings.ff("%s%s", var7.HF, var7.LK);
         }
      }

      return null;
   }

   public long q(long var1, String var3) throws IOException, zy {
      long var4 = Conversion.stringToLong(var3);
      if (var4 != 0L) {
         return var4;
      } else {
         String var6 = var3;
         String var7 = null;
         int var8 = var3.indexOf(40);
         if (var8 != -1) {
            var6 = var3.substring(0, var8);
            var7 = var3.substring(var8);
         }

         return this.Dw(var1, var6, var7);
      }
   }

   public long Dw(long var1, String var3, String var4) throws IOException, zy {
      ih var5 = this.RF(var1, var3, var4);
      return var5 == null ? -1L : var5.JY;
   }

   public ih q(long var1, String var3, List var4) throws IOException, zy {
      long var5 = Conversion.stringToLong(var3);
      if (var5 != 0L) {
         return this.RF(var1, var5);
      } else {
         ih var7 = null;
         String var8 = var3;
         String var9 = null;
         int var10 = var3.indexOf(40);
         if (var10 != -1) {
            var8 = var3.substring(0, var10);
            var9 = var3.substring(var10);
         }

         while (var1 != 0L && var7 == null) {
            List var11 = this.q(var1, var8, var9);
            var7 = this.q(var8, var11, var4);
            var1 = this.za(var1);
         }

         return var7;
      }
   }

   private ih q(String var1, List var2, List var3) throws IOException, zy {
      if (var2.isEmpty()) {
         return null;
      } else {
         TreeSet var4 = new TreeSet();

         for (ih var6 : var2) {
            if (var6.HF.equals(var1)) {
               String var7 = var6.LK;
               if (var7.charAt(0) == '(') {
                  String var8 = var7.substring(1, var7.indexOf(41));
                  List var9 = this.oW(var8);
                  if (var3.size() == var9.size()) {
                     boolean var10 = true;

                     for (int var11 = 0; var11 < var3.size(); var11++) {
                        if (!this.q((ch)var3.get(var11), (String)var9.get(var11))) {
                           var10 = false;
                           break;
                        }
                     }

                     if (var10) {
                        return var6;
                     }
                  }
               }

               var4.add(var6.LK);
            }
         }

         if (!var4.isEmpty()) {
            StringBuilder var12 = new StringBuilder("Invalid arguments for ").append(var1);
            if (var4.size() == 1) {
               var12.append(". Signature: ").append((String)var4.iterator().next());
            } else {
               var12.append(". Valid signatures: ").append(Strings.join(", ", var4));
            }

            throw new zy(var12.toString());
         } else {
            return null;
         }
      }
   }

   private List oW(String var1) {
      ArrayList var2 = new ArrayList();

      while (!var1.isEmpty()) {
         String var3 = this.gO(var1);
         var2.add(var3);
         var1 = var1.substring(var3.length());
      }

      return var2;
   }

   private String gO(String var1) {
      switch (var1.charAt(0)) {
         case 'L':
            return var1.substring(0, var1.indexOf(59) + 1);
         case '[':
            return "[" + this.gO(var1.substring(1));
         default:
            return var1.substring(0, 1);
      }
   }

   public boolean q(ch var1, String var2) throws IOException, zy {
      byte var3 = var1.q;
      switch (var3) {
         case 66:
            return "B".equals(var2);
         case 67:
            return "C".equals(var2);
         case 68:
            return "D".equals(var2);
         case 69:
         case 71:
         case 72:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         default:
            if (var2.startsWith("L")) {
               if (var2.equals("Ljava/lang/Object;")) {
                  return true;
               }

               long var7 = this.RF.HF().q(var1.RF);
               return this.xK(var7, var2);
            } else {
               if (var2.startsWith("[") && var3 == 91) {
                  long var4 = this.RF.HF().q(var1.RF);

                  String var6;
                  for (var6 = this.oW(var4); var2.startsWith("["); var6 = var6.substring(1)) {
                     var2 = var2.substring(1);
                     if (!var6.startsWith("[")) {
                        return false;
                     }
                  }

                  if (var6.startsWith("[")) {
                     return false;
                  }

                  return this.xK(this.Dw(var6), var2);
               }

               return false;
            }
         case 70:
            return "F".equals(var2);
         case 73:
            return "I".equals(var2);
         case 74:
            return "J".equals(var2);
         case 83:
            return "S".equals(var2);
         case 90:
            return "Z".equals(var2);
      }
   }

   private boolean xK(long var1, String var3) throws IOException, zy {
      String var4 = this.oW(var1);
      long var5 = this.Dw(var3);
      if (!var4.equals(var3) && var5 != var1) {
         long var7 = this.za(var1);
         if (var7 == 0L) {
            return false;
         } else if (this.xK(var7, var3)) {
            return true;
         } else {
            for (long var11 : this.lm(var1)) {
               if (this.xK(var11, var3)) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   public long RF(long var1, String var3) throws IOException, zy {
      long var4 = Conversion.stringToLong(var3);
      if (var4 != 0L) {
         return var4;
      } else {
         String var6 = var3;
         String var7 = null;
         int var8 = var3.indexOf(58);
         if (var8 != -1) {
            var6 = var3.substring(0, var8);
            var7 = var3.substring(var8 + 1);
         }

         return this.Uv(var1, var6, var7);
      }
   }

   public long Uv(long var1, String var3, String var4) throws IOException, zy {
      hw var5 = this.xK(var1, var3, var4);
      return var5 == null ? -1L : var5.za;
   }

   public String q(oG var1) throws IOException, zy {
      String var2 = this.oW(var1.xK);
      String var3 = this.q(var1.xK, var1.Dw);
      return Strings.ff("%s->%s @ %d (offset: %Xh)", var2, var3, var1.Uv, 2L * var1.Uv);
   }

   public String RF(oG var1) throws IOException, zy {
      String var2 = this.oW(var1.xK);
      String var3 = this.q(var1.xK, var1.Dw);
      return Strings.ff("%s->%s+%Xh", var2, var3, 2L * var1.Uv);
   }

   void q() throws IOException {
      if (!this.RF.q()) {
         throw new IOException("The debugger must be connected in order to complete the operation");
      }
   }

   public String RF() {
      try {
         this.q();
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "VM information: %s\n", this.RF.Dw.q());
         Strings.ff(var1, "VM identifier sizes: %s", this.RF.Dw.Uv());
         return var1.toString();
      } catch (Fx | IOException var2) {
         q.catching(var2);
         return null;
      }
   }

   public boolean xK() {
      try {
         this.q();
         this.RF.Dw.gO();
         return true;
      } catch (Fx | IOException var2) {
         q.catching(var2);
         return false;
      }
   }

   public boolean Dw() {
      try {
         this.q();
         this.RF.Dw.oW();
         return true;
      } catch (Fx | IOException var2) {
         q.catching(var2);
         return false;
      }
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   public boolean Uv() {
      List var1;
      try {
         var1 = this.oW();
         if (var1 == null) {
            return false;
         }
      } catch (Fx | IOException var8) {
         q.catching(var8);
         return false;
      }

      Iterator var2;
      try {
         var2 = var1.iterator();
      } catch (Fx | IOException var6) {
         q.catching(var6);
         return false;
      }

      while (true) {
         try {
            if (!var2.hasNext()) {
               return true;
            }

            long var3 = (Long)var2.next();
            int var5 = this.RF.Dw.qa(var3);
            Object[] var10000 = new Object[]{var3, var5};
            if (var5 == 0) {
               return false;
            }
         } catch (Fx | IOException var7) {
            q.catching(var7);
            return false;
         }
      }
   }

   public List oW() {
      try {
         this.q();
         Uc var1 = this.RF.Dw.RF();
         return ArrayUtil.asList(var1.RF);
      } catch (Fx | IOException var2) {
         q.catching(var2);
         return null;
      }
   }

   public String gO(long var1) {
      try {
         return this.RF.Dw.za(var1);
      } catch (Fx | IOException var4) {
         q.catching(var4);
         return null;
      }
   }

   public boolean nf(long var1) {
      try {
         this.RF.Dw.lm(var1);
         return true;
      } catch (Fx | IOException var4) {
         q.catching(var4);
         return false;
      }
   }

   public boolean q(long var1, boolean var3) {
      try {
         int var4 = this.RF.Dw.qa(var1);

         do {
            this.RF.Dw.q(var1, var3);
         } while (--var4 > 0);

         return true;
      } catch (Fx | IOException var5) {
         q.catching(var5);
         return false;
      }
   }

   public DebuggerThreadStatus gP(long var1) {
      try {
         TG var3 = this.RF.Dw.JY(var1);
         if (var3.RF == 1) {
            return DebuggerThreadStatus.PAUSED;
         } else {
            switch (jw.q(var3.q)) {
               case Uv:
                  return DebuggerThreadStatus.MONITOR;
               case xK:
                  return DebuggerThreadStatus.RUNNING;
               case Dw:
                  return DebuggerThreadStatus.SLEEPING;
               case oW:
                  return DebuggerThreadStatus.WAITING;
               case RF:
                  return DebuggerThreadStatus.ZOMBIE;
               default:
                  return DebuggerThreadStatus.UNKNOWN;
            }
         }
      } catch (Fx | IOException var4) {
         q.catching(var4);
         return DebuggerThreadStatus.UNKNOWN;
      }
   }

   public boolean RF(boolean var1) {
      try {
         if (var1) {
            this.RF(false);
            boolean var2 = false;
            if (this.gO == null) {
               m var3 = var2 ? m.RF : m.q;
               this.gO = this.q(dN.oW, var3, null);
            }

            if (this.nf == null) {
               this.nf = this.q(dN.gO, m.q, null);
            }
         } else {
            if (this.gO != null) {
               this.q(this.gO);
               this.gO = null;
            }

            if (this.nf != null) {
               this.q(this.nf);
               this.nf = null;
            }
         }

         return true;
      } catch (Fx | IOException var4) {
         q.catching(var4);
         return false;
      }
   }

   public boolean xK(boolean var1) {
      try {
         if (var1) {
            if (this.gP == null) {
               this.gP = this.q(dN.nf, m.q, null);
            }
         } else if (this.gP != null) {
            this.q(this.gP);
            this.gP = null;
         }

         return true;
      } catch (Fx | IOException var3) {
         q.catching(var3);
         return false;
      }
   }

   public boolean Dw(boolean var1) {
      try {
         if (var1) {
            if (this.za == null) {
               to var2 = new to();
               Vm var3 = Vm.q(0L, false, true);
               var2.q(var3);
               this.za = this.q(dN.Dw, this.RF.qa(), var2);
            }
         } else if (this.za != null) {
            this.q(this.za);
            this.za = null;
         }

         return true;
      } catch (Fx | IOException var4) {
         q.catching(var4);
         return false;
      }
   }

   public aB q(dN var1, m var2, to var3) throws IOException, Fx {
      int var4 = this.RF.zz().q(var1, var2, var3);
      Object[] var10000 = new Object[]{var4, var1};
      return new aB(var1, var4);
   }

   public void q(aB var1) throws IOException, Fx {
      Object[] var10000 = new Object[]{var1.RF()};
      this.RF.zz().q(var1.q(), var1.RF());
   }

   public long za(long var1) throws IOException, zy {
      synchronized (this.oW) {
         Long var4 = (Long)this.oW.get(var1);
         if (var4 == null) {
            try {
               var4 = this.RF.Dw.oW(var1);
               this.oW.put(var1, var4);
            } catch (Fx var7) {
               throw new zy(var7, "Error while retrieving superclass of class %d", var1);
            }
         }

         return var4;
      }
   }

   public List lm(long var1) throws IOException, zy {
      try {
         return this.RF.Dw.RF(var1);
      } catch (Fx var4) {
         throw new zy(var4, "Error while retrieving interfaces of class %d", var1);
      }
   }

   public ch q(long var1, long var3, long var5, String var7, List var8) throws zy, IOException {
      ih var9 = this.q(var5, var7, var8);
      if (var9 == null) {
         throw new zy(Strings.ff("Invalid method %s for class %s", var7, this.oW(var5)));
      } else if (var9.q(8)) {
         try {
            return this.RF.zz().q(var5, var1, var9.JY, var8);
         } catch (Fx var11) {
            throw new zy(var11, this.q(var11, var9.JY, var5, true));
         }
      } else if (var3 <= 0L) {
         throw new zy(Strings.ff("Method %s is not static", var7));
      } else {
         try {
            return this.RF.zz().q(var3, var1, var5, var9.JY, var8);
         } catch (Fx var12) {
            throw new zy(var12, this.q(var12, var9.JY, var3, false));
         }
      }
   }

   private String q(Fx var1, long var2, long var4, boolean var6) {
      return Strings.ff(
         "Error while trying to invoke %s method %d on %s %d%s",
         var6 ? "static" : "instance",
         var2,
         var6 ? "class" : "object",
         var4,
         var1.RF() == 10 ? ": Method invocation is not supported when the target VM has been suspended by the front-end, it must be suspended by an event" : ""
      );
   }

   public ch q(long var1, long var3, List var5) throws zy, IOException {
      ih var6 = this.q(var3, "<init>", var5);

      try {
         return this.RF.zz().RF(var3, var1, var6.JY, var5);
      } catch (Fx var8) {
         throw new zy(var8, this.q(var8, var6.JY, var3));
      }
   }

   private String q(Fx var1, long var2, long var4) {
      return Strings.ff(
         "Error while trying to instanciate %d of class %d%s",
         var2,
         var4,
         var1.RF() == 10 ? ": Method invocation is not supported when the target VM has been suspended by the front-end, it must be suspended by an event" : ""
      );
   }
}
