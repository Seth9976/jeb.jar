package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bu {
   private static final ILogger Dw = GlobalLog.getLogger(Bu.class);
   private int Uv = 0;
   Map q = new HashMap();
   Map RF = new HashMap();
   Map xK = new HashMap();

   public Bu.eo q(Object var1) {
      if (this.q.containsKey(var1)) {
         return (Bu.eo)this.q.get(var1);
      } else {
         Bu.eo var2 = new Bu.eo(this.Uv, var1);
         this.Uv++;
         this.q.put(var1, var2);
         return var2;
      }
   }

   public List q() {
      ArrayList var1 = new ArrayList();
      HashSet var2 = new HashSet();

      for (Bu.eo var4 : this.q.values()) {
         if (!var2.contains(var4)) {
            Set var5 = this.q(var4);
            var1.add(var5);
            var2.addAll(var5);
         }
      }

      return var1;
   }

   public Set q(Bu.eo var1) {
      HashSet var2 = new HashSet();
      HashSet var3 = new HashSet();
      if (this.RF.get(var1) != null) {
         var3.addAll((Collection)this.RF.get(var1));
      }

      if (this.xK.get(var1) != null) {
         var3.addAll((Collection)this.xK.get(var1));
      }

      if (!var3.isEmpty()) {
         while (!var3.isEmpty()) {
            Bu.eo var4 = (Bu.eo)var3.iterator().next();
            var3.remove(var4);
            if (!var2.contains(var4)) {
               var2.add(var4);
               if (this.RF.get(var4) != null) {
                  var3.addAll((Collection)this.RF.get(var4));
               }

               if (this.xK.get(var4) != null) {
                  var3.addAll((Collection)this.xK.get(var4));
               }
            }
         }
      }

      return var2;
   }

   public void q(Bu.eo var1, Bu.eo var2) {
      if (!this.RF.containsKey(var1)) {
         HashSet var3 = new HashSet();
         this.RF.put(var1, var3);
      }

      ((Set)this.RF.get(var1)).add(var2);
      if (!this.xK.containsKey(var2)) {
         HashSet var4 = new HashSet();
         this.xK.put(var2, var4);
      }

      ((Set)this.xK.get(var2)).add(var1);
   }

   public String q(String var1, String var2, Set var3) throws IOException {
      StringBuilder var4 = new StringBuilder();
      var4.append("digraph G {\n");

      for (Bu.eo var6 : this.q.values()) {
         if (var3 == null || var3.contains(var6)) {
            String var7 = (String)var6.RF();
            var7 = var7.replaceAll("->", ":");
            if (var7.length() >= 20) {
               var7 = var7.substring(0, 19);
               var7 = var7 + "...";
            }

            Strings.ff(var4, "N%08x [shape=Mrecord,fontname=\"Consolas\",style=\"filled\",color=\"%s\",label=\"%s\"];\n", var6.q(), var6.xK(), var7);
         }
      }

      for (Bu.eo var11 : this.RF.keySet()) {
         if (var3 == null || var3.contains(var11)) {
            for (Bu.eo var8 : (Set)this.RF.get(var11)) {
               if (var3 == null || var3.contains(var11)) {
                  Strings.ff(var4, "N%08x -> N%08x\n", var11.q(), var8.q());
               }
            }
         }
      }

      var4.append("}");
      File var10 = new File(var1, var2 + ".dot");
      IO.writeFile(var10, var4.toString());
      return IO.abs(var10);
   }

   public static class eo {
      int q;
      Object RF;
      String xK = "grey";

      public eo(int var1, Object var2) {
         this.q = var1;
         this.RF = var2;
      }

      public int q() {
         return this.q;
      }

      public Object RF() {
         return this.RF;
      }

      public void q(String var1) {
         this.xK = var1;
      }

      public String xK() {
         return this.xK;
      }
   }
}
