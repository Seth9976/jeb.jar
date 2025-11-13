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
public class Av extends HeadlessClientContext {
   private static final ILogger pC = GlobalLog.getLogger(Av.class);
   private boolean A;
   private String kS;
   private String wS;
   private String[] UT;

   public Av(boolean var1, String var2, String var3, String[] var4) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4 == null ? ArrayUtil.NO_STRING : var4;
   }

   @Override
   public void start() throws JebException {
      super.start();
      if (this.kS == null) {
         throw new IllegalArgumentException("An input script path must be specified");
      } else {
         String var1 = this.getPropertyManager().getString(".ScriptsFolder");
         if (this.wS == null) {
            this.wS = var1;
            if (this.wS == null) {
               throw new IllegalArgumentException("A library folder must be specified");
            }
         }

         try {
            ScriptLoader var2 = new ScriptLoader(this.kS, this.wS);
            var2.execute(this);
         } catch (ScriptPreparationException var3) {
            if (this.isDevelopmentMode()) {
               pC.catching(var3);
            }

            pC.error(S.L("Script preparation error: %s"), var3.getMessage());
         } catch (ScriptInitializationException var4) {
            if (this.isDevelopmentMode()) {
               pC.catching(var4);
            }

            pC.error(S.L("Script initialization error: %s"), var4.getMessage());
         } catch (ScriptExecutionException var5) {
            if (this.isDevelopmentMode()) {
               pC.catching(var5);
            }

            pC.error(S.L("Script execution error: %s"), var5.getMessage());
         } catch (ScriptException var6) {
            pC.error(S.L("An exception was thrown when executing the script:"));
            if (this.isDevelopmentMode()) {
               pC.catching(var6);
            } else {
               pC.error(Throwables.formatStacktraceShort(var6));
            }
         }
      }
   }

   @Override
   public String[] getArguments() {
      return this.UT;
   }

   @Override
   public boolean isDevelopmentMode() {
      return this.A;
   }
}
