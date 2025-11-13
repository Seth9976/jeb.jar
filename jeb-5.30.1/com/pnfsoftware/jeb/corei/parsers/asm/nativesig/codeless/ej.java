package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.CodelessSignaturePackageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.ExecutableModelMetadata;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.Func;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.ModuleId;
import com.pnfsoftware.jeb.util.collect.SetMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.DirectByteArrayOutputStream;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationException;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.Serializer;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.cuu;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Ser
public class ej {
   private static final ILogger JY = GlobalLog.getLogger(ej.class, 30);
   public static final String q = "codeless-sig.model";
   @SerId(1)
   ExecutableModelMetadata RF;
   @SerId(2)
   SetMap xK = new SetMap();
   @SerId(3)
   SetMap Dw = new SetMap();
   @SerId(4)
   SetMap Uv = new SetMap();
   @SerId(5)
   SetMap oW = new SetMap();
   @SerId(6)
   SetMap gO = new SetMap();
   @SerId(7)
   SetMap nf = new SetMap();
   @SerId(8)
   SetMap gP = new SetMap();
   @SerId(9)
   SetMap za = new SetMap();
   @SerId(10)
   Set lm = new HashSet();
   @SerTransient
   CodelessSignaturePackageEntry zz;

   public Set q() {
      return this.Uv.values();
   }

   public void q(ModuleId var1, Func var2) {
      this.Uv.put(var1, var2);
   }

   public SetMap RF() {
      return this.xK;
   }

   public SetMap xK() {
      return this.Dw;
   }

   public SetMap Dw() {
      return this.oW;
   }

   public SetMap Uv() {
      return this.gO;
   }

   public void q(SetMap var1) {
      this.oW = var1;
   }

   public void RF(SetMap var1) {
      this.gO = var1;
   }

   public SetMap oW() {
      return this.nf;
   }

   public void xK(SetMap var1) {
      this.nf = var1;
   }

   public SetMap gO() {
      return this.gP;
   }

   public void Dw(SetMap var1) {
      this.gP = var1;
   }

   public SetMap nf() {
      return this.Uv;
   }

   public SetMap gP() {
      return this.za;
   }

   public Set za() {
      return this.lm;
   }

   public String lm() {
      ArrayList var1 = new ArrayList();

      for (ModuleId var3 : this.Uv.keySet()) {
         List var4 = (List)this.Uv.get(var3).stream().map(var0 -> var0.getName()).collect(Collectors.toList());
         Collections.sort(var4);
         var1.add(Strings.ff("> %s routines: %s", var3.getFileName(), var4));
      }

      for (tw var12 : this.xK.keySet()) {
         List var19 = (List)this.xK.get(var12).stream().map(var0 -> var0.getName()).collect(Collectors.toList());
         Collections.sort(var19);
         var1.add(Strings.ff("> %s present in %s", var12, var19));
      }

      for (Func var13 : this.oW.keySet()) {
         List var20 = (List)this.oW.get(var13).stream().map(var0 -> var0.getName()).collect(Collectors.toList());
         Collections.sort(var20);
         var1.add(Strings.ff("> %s calls to %s", var13, var20));
      }

      for (Func var14 : this.gO.keySet()) {
         List var21 = (List)this.gO.get(var14).stream().map(var0 -> var0.getName()).collect(Collectors.toList());
         Collections.sort(var21);
         var1.add(Strings.ff("> %s is called from %s", var14, var21));
      }

      for (Func var15 : this.nf.keySet()) {
         List var22 = (List)this.nf.get(var15).stream().map(var0 -> var0.getName()).collect(Collectors.toList());
         Collections.sort(var22);
         var1.add(Strings.ff("> %s ref to %s", var15, var22));
      }

      for (Func var16 : this.gP.keySet()) {
         List var23 = (List)this.gP.get(var16).stream().map(var0 -> var0.getName()).collect(Collectors.toList());
         Collections.sort(var23);
         var1.add(Strings.ff("> %s is ref from %s", var16, var23));
      }

      for (tw var17 : this.lm) {
         var1.add(Strings.ff("> unref feat: %s", var17));
      }

      if (!this.za.isEmpty()) {
         var1.add(Strings.ff("> twin sets (%d)", this.za.size()));

         for (Long var18 : this.za.keySet()) {
            List var24 = (List)this.za.get(var18).stream().map(var0 -> var0.getName()).collect(Collectors.toList());
            Collections.sort(var24);
            var1.add(Strings.ff("> twin hash %x: %s", var18, var24));
         }
      }

      Collections.sort(var1);
      return Strings.joinv("", var1.toArray());
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("modules definitions:");
      var1.append(Strings.LINESEP);

      for (ModuleId var3 : this.Uv.keySet()) {
         Strings.ff(var1, "> %s routines: %s", var3.getFileName(), this.Uv.get(var3));
         var1.append(Strings.LINESEP);
      }

      var1.append("features:");
      var1.append(Strings.LINESEP);

      for (tw var11 : this.xK.keySet()) {
         Strings.ff(var1, "> %s present in %s", var11, this.xK.get(var11));
         var1.append(Strings.LINESEP);
      }

      var1.append("callgraph:");
      var1.append(Strings.LINESEP);

      for (Func var12 : this.oW.keySet()) {
         Strings.ff(var1, "> %s calls to %s", var12, this.oW.get(var12));
         var1.append(Strings.LINESEP);
      }

      for (Func var13 : this.gO.keySet()) {
         Strings.ff(var1, "> %s is called from %s", var13, this.gO.get(var13));
         var1.append(Strings.LINESEP);
      }

      var1.append("references:");
      var1.append(Strings.LINESEP);

      for (Func var14 : this.nf.keySet()) {
         Strings.ff(var1, "> %s ref to %s", var14, this.nf.get(var14));
         var1.append(Strings.LINESEP);
      }

      for (Func var15 : this.gP.keySet()) {
         Strings.ff(var1, "> %s is ref from %s", var15, this.gP.get(var15));
         var1.append(Strings.LINESEP);
      }

      for (tw var16 : this.lm) {
         Strings.ff(var1, "> unref feat: %s", var16);
         var1.append(Strings.LINESEP);
      }

      if (!this.za.isEmpty()) {
         Strings.ff(var1, "> twin sets (%d)", this.za.size());
         var1.append(Strings.LINESEP);

         for (Long var17 : this.za.keySet()) {
            Strings.ff(var1, "> twin hash %x: %s", var17, this.za.get(var17));
            var1.append(Strings.LINESEP);
         }
      }

      return var1.toString();
   }

   public static ExecutableModelMetadata q(File var0) {
      ExecutableModelMetadata var1 = null;

      try (FileInputStream var2 = new FileInputStream(var0)) {
         cuu var3 = cuu.q();
         SerializationManager var4 = new SerializationManager(var3);
         Deserializer var5 = var4.getDeserializer(var2);
         var1 = (ExecutableModelMetadata)var5.deserialize();
      } catch (IOException var8) {
      }

      return var1;
   }

   public static ej RF(File var0) {
      ej var1 = null;

      try (FileInputStream var2 = new FileInputStream(var0)) {
         cuu var3 = cuu.q();
         SerializationManager var4 = new SerializationManager(var3);
         Deserializer var5 = var4.getDeserializer(var2);
         var5.deserialize();
         var1 = (ej)var5.deserialize();
      } catch (IOException var8) {
      }

      return var1;
   }

   public static ej q(String var0) {
      return RF(new File(var0));
   }

   public static void q(ej var0, String var1) throws IOException {
      cuu var2 = cuu.q();
      SerializationManager var3 = new SerializationManager(var2);
      DirectByteArrayOutputStream var4 = new DirectByteArrayOutputStream();
      Serializer var5 = var3.getSerializer(var4);

      try {
         var5.serialize(var0.JY());
         var5.serialize(var0);
         var5.close();
      } catch (SerializationException var8) {
         JY.catching(var8);
      }

      File var6 = new File(var1);
      if (!var6.exists()) {
         IO.createDirectory(var6);
      }

      File var7 = new File(var1.concat(File.separator).concat("codeless-sig.model"));
      IO.writeFile(var7, var4.getRawBytes(), 0, var4.size());
   }

   public CodelessSignaturePackageEntry zz() {
      return this.zz;
   }

   public void q(CodelessSignaturePackageEntry var1) {
      this.zz = var1;
   }

   public ExecutableModelMetadata JY() {
      return this.RF;
   }

   public void q(ExecutableModelMetadata var1) {
      this.RF = var1;
   }
}
