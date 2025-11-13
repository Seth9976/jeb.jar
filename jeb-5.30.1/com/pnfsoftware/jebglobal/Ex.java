package com.pnfsoftware.jebglobal;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class Ex extends oV {
   protected final Map oW = new LinkedHashMap();

   protected Ex(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
   }

   @Override
   protected void q(ByteBuffer var1) {
      super.q(var1);
      this.oW.clear();
      int var2 = this.Uv + this.Dw();
      int var3 = var2;
      int var4 = this.Uv + this.Uv();
      int var5 = var1.position();
      var1.position(var2);

      while (var3 < var4) {
         oV var6 = this.Uv(var1);
         this.oW.put(var3, var6);
         var3 += var6.Uv();
      }

      var1.position(var5);
   }

   public final Map gO() {
      return this.oW;
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      for (oV var5 : this.gO().values()) {
         byte[] var6 = var5.q(var3);
         var1.write(var6);
         q(var1, var6.length);
      }
   }

   public oV Uv(ByteBuffer var1) {
      return this.RF(var1, this);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private oV RF(ByteBuffer var1, @Nullable oV var2) {
      oV.eo var4 = oV.eo.q(var1.getShort());

      Object var3 = switch (var4) {
         case RF -> new pV(var1, var2);
         case xK -> new Xm(var1, var2);
         case Dw -> new ec(var1, var2);
         case Uv -> new BC(var1, var2);
         case oW -> new LG(var1, var2);
         case gO -> new vy(var1, var2);
         case nf -> new Es(var1, var2);
         case gP -> new Jj(var1, var2);
         case za -> new rM(var1, var2);
         case lm -> new Wo(var1, var2);
         case zz -> new PB(var1, var2);
         case JY -> new Vz(var1, var2);
         case HF -> new bx(var1, var2);
         default -> new Vs(var1, var2);
      };
      ((oV)var3).q(var1);
      ((oV)var3).RF(var1);
      return (oV)var3;
   }
}
