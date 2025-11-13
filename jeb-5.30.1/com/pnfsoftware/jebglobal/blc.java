package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDSandboxHooks;
import com.pnfsoftware.jeb.util.base.Wrapper;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.util.List;

public class blc implements IDSandboxHooks {
   private ILogger q;

   public blc(ILogger var1) {
      this.q = var1;
   }

   @Override
   public Class loadClass(String var1) {
      this.q.debug("LOADING: %s", var1);
      return null;
   }

   @Override
   public Boolean setField(long var1, String var3, String var4, Object var5, Object[] var6) {
      this.q.debug("[%d] SETTING FIELD: %s on %s (FROM: %s)", var1, var4, this.q(var5), var3);
      this.q.debug("    %s", this.q(var6[0]));
      return null;
   }

   @Override
   public Wrapper getField(long var1, String var3, String var4, Object var5) {
      this.q.debug("[%d] GETTING FIELD: %s on %s (FROM: %s)", var1, var4, this.q(var5), var3);
      return null;
   }

   @Override
   public Wrapper examineFieldValue(long var1, Object var3) {
      this.q.debug("[%d] VALUE => %s", var1, this.q(var3));
      return null;
   }

   @Override
   public Wrapper invokeMethod(long var1, String var3, String var4, Object var5, List var6) {
      this.q.debug("[%d] INVOKING: %s on %s (FROM: %s)", var1, var4, this.q(var5), var3);

      for (Object var8 : var6) {
         this.q.debug("    %s", this.q(var8));
      }

      return null;
   }

   @Override
   public Wrapper examineMethodResult(long var1, Object var3) {
      this.q.debug("[%d] RETURN => %s", var1, this.q(var3));
      return null;
   }

   @Override
   public Wrapper newInstance(long var1, String var3, String var4, List var5) {
      this.q.debug("[%d] NEW: %s (FROM: %s)", var1, var4, var3);

      for (Object var7 : var5) {
         this.q.debug("    %s", this.q(var7));
      }

      return null;
   }

   @Override
   public void examineCreatedInstance(long var1, Object var3) {
      this.q.debug("[%d] INSTANCE => %s", var1, this.q(var3));
   }

   public StringBuilder q(Object var1) {
      StringBuilder var2 = new StringBuilder();
      this.q(var1, var2);
      return var2;
   }

   public void q(Object var1, StringBuilder var2) {
      if (var1 instanceof Class) {
         var2.append(((Class)var1).getSimpleName());
      } else if (var1 instanceof Executable) {
         var2.append(((Executable)var1).getName());
      } else if (var1 instanceof Field) {
         var2.append(((Field)var1).getName());
      } else if (!(var1 instanceof String) && !(var1 instanceof Number)) {
         var2.append("<complex>");
      } else {
         var2.append(Formatter.toString(var1));
      }
   }
}
