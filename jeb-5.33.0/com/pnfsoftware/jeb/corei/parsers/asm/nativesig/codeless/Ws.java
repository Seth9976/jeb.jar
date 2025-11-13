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
import com.pnfsoftware.jebglobal.ckh;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Ser
public class Ws {
   private static final ILogger fI = GlobalLog.getLogger(Ws.class, 30);
   @SerId(1)
   ExecutableModelMetadata pC;
   @SerId(2)
   SetMap A = new SetMap();
   @SerId(3)
   SetMap kS = new SetMap();
   @SerId(4)
   SetMap wS = new SetMap();
   @SerId(5)
   SetMap UT = new SetMap();
   @SerId(6)
   SetMap E = new SetMap();
   @SerId(7)
   SetMap sY = new SetMap();
   @SerId(8)
   SetMap ys = new SetMap();
   @SerId(9)
   SetMap ld = new SetMap();
   @SerId(10)
   Set gp = new HashSet();
   @SerTransient
   CodelessSignaturePackageEntry oT;

   public Set pC() {
      return this.wS.values();
   }

   public void pC(ModuleId var1, Func var2) {
      this.wS.put(var1, var2);
   }

   public SetMap A() {
      return this.A;
   }

   public SetMap kS() {
      return this.kS;
   }

   public SetMap wS() {
      return this.UT;
   }

   public SetMap UT() {
      return this.E;
   }

   public void pC(SetMap var1) {
      this.UT = var1;
   }

   public SetMap E() {
      return this.sY;
   }

   public void A(SetMap var1) {
      this.sY = var1;
   }

   public SetMap sY() {
      return this.ys;
   }

   public SetMap ys() {
      return this.wS;
   }

   public SetMap ld() {
      return this.ld;
   }

   public Set gp() {
      return this.gp;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("modules definitions:");
      var1.append(Strings.LINESEP);

      for (ModuleId var3 : this.wS.keySet()) {
         Strings.ff(var1, "> %s routines: %s", var3.getFileName(), this.wS.get(var3));
         var1.append(Strings.LINESEP);
      }

      var1.append("features:");
      var1.append(Strings.LINESEP);

      for (rQ var11 : this.A.keySet()) {
         Strings.ff(var1, "> %s present in %s", var11, this.A.get(var11));
         var1.append(Strings.LINESEP);
      }

      var1.append("callgraph:");
      var1.append(Strings.LINESEP);

      for (Func var12 : this.UT.keySet()) {
         Strings.ff(var1, "> %s calls to %s", var12, this.UT.get(var12));
         var1.append(Strings.LINESEP);
      }

      for (Func var13 : this.E.keySet()) {
         Strings.ff(var1, "> %s is called from %s", var13, this.E.get(var13));
         var1.append(Strings.LINESEP);
      }

      var1.append("references:");
      var1.append(Strings.LINESEP);

      for (Func var14 : this.sY.keySet()) {
         Strings.ff(var1, "> %s ref to %s", var14, this.sY.get(var14));
         var1.append(Strings.LINESEP);
      }

      for (Func var15 : this.ys.keySet()) {
         Strings.ff(var1, "> %s is ref from %s", var15, this.ys.get(var15));
         var1.append(Strings.LINESEP);
      }

      for (rQ var16 : this.gp) {
         Strings.ff(var1, "> unref feat: %s", var16);
         var1.append(Strings.LINESEP);
      }

      if (!this.ld.isEmpty()) {
         Strings.ff(var1, "> twin sets (%d)", this.ld.size());
         var1.append(Strings.LINESEP);

         for (Long var17 : this.ld.keySet()) {
            Strings.ff(var1, "> twin hash %x: %s", var17, this.ld.get(var17));
            var1.append(Strings.LINESEP);
         }
      }

      return var1.toString();
   }

   public static ExecutableModelMetadata pC(File var0) {
      ExecutableModelMetadata var1 = null;

      try (FileInputStream var2 = new FileInputStream(var0)) {
         ckh var3 = ckh.pC();
         SerializationManager var4 = new SerializationManager(var3);
         Deserializer var5 = var4.getDeserializer(var2);
         var1 = (ExecutableModelMetadata)var5.deserialize();
      } catch (IOException var8) {
      }

      return var1;
   }

   public static Ws A(File var0) {
      Ws var1 = null;

      try (FileInputStream var2 = new FileInputStream(var0)) {
         ckh var3 = ckh.pC();
         SerializationManager var4 = new SerializationManager(var3);
         Deserializer var5 = var4.getDeserializer(var2);
         var5.deserialize();
         var1 = (Ws)var5.deserialize();
      } catch (IOException var8) {
      }

      return var1;
   }

   public static Ws pC(String var0) {
      return A(new File(var0));
   }

   public static void pC(Ws var0, String var1) throws IOException {
      ckh var2 = ckh.pC();
      SerializationManager var3 = new SerializationManager(var2);
      DirectByteArrayOutputStream var4 = new DirectByteArrayOutputStream();
      Serializer var5 = var3.getSerializer(var4);

      try {
         var5.serialize(var0.fI());
         var5.serialize(var0);
         var5.close();
      } catch (SerializationException var8) {
         fI.catching(var8);
      }

      File var6 = new File(var1);
      if (!var6.exists()) {
         IO.createDirectory(var6);
      }

      File var7 = new File(var1.concat(File.separator).concat("codeless-sig.model"));
      IO.writeFile(var7, var4.getRawBytes(), 0, var4.size());
   }

   public CodelessSignaturePackageEntry oT() {
      return this.oT;
   }

   public void pC(CodelessSignaturePackageEntry var1) {
      this.oT = var1;
   }

   public ExecutableModelMetadata fI() {
      return this.pC;
   }

   public void pC(ExecutableModelMetadata var1) {
      this.pC = var1;
   }
}
