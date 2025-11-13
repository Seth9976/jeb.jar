package com.pnfsoftware.jeb;

import com.pnfsoftware.jeb.client.HeadlessClientContext;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.client.script.ScriptException;
import com.pnfsoftware.jeb.client.script.ScriptExecutionException;
import com.pnfsoftware.jeb.client.script.ScriptInitializationException;
import com.pnfsoftware.jeb.client.script.ScriptLoader;
import com.pnfsoftware.jeb.client.script.ScriptPreparationException;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class eo extends HeadlessClientContext {
   private static final ILogger q = GlobalLog.getLogger(eo.class);
   private boolean RF;
   private String xK;
   private String Dw;
   private String[] Uv;

   public eo(boolean var1, String var2, String var3, String[] var4) {
      this.RF = var1;
      this.xK = var2;
      this.Dw = var3;
      this.Uv = var4 == null ? ArrayUtil.NO_STRING : var4;
   }

   @Override
   public void start() throws JebException {
      super.start();
      if (this.xK == null) {
         throw new IllegalArgumentException("An input script path must be specified");
      } else {
         String var1 = this.getPropertyManager().getString(".ScriptsFolder");
         if (this.Dw == null) {
            this.Dw = var1;
            if (this.Dw == null) {
               throw new IllegalArgumentException("A library folder must be specified");
            }
         }

         try {
            ScriptLoader var2 = new ScriptLoader(this.xK, this.Dw);
            var2.execute(this);
         } catch (ScriptPreparationException var3) {
            if (this.isDevelopmentMode()) {
               q.catching(var3);
            }

            q.error(S.L("Script preparation error: %s"), var3.getMessage());
         } catch (ScriptInitializationException var4) {
            if (this.isDevelopmentMode()) {
               q.catching(var4);
            }

            q.error(S.L("Script initialization error: %s"), var4.getMessage());
         } catch (ScriptExecutionException var5) {
            if (this.isDevelopmentMode()) {
               q.catching(var5);
            }

            q.error(S.L("Script execution error: %s"), var5.getMessage());
         } catch (ScriptException var6) {
            q.error(S.L("An exception was thrown when executing the script:"));
            if (this.isDevelopmentMode()) {
               q.catching(var6);
            } else {
               q.error(Throwables.formatStacktraceShort(var6));
            }
         }
      }
   }

   @Override
   public String[] getArguments() {
      return this.Uv;
   }

   @Override
   public boolean isDevelopmentMode() {
      return this.RF;
   }
}
