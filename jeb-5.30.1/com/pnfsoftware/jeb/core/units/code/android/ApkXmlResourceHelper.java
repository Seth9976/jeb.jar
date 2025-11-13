package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.IXmlUnit;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ApkXmlResourceHelper extends XmlResourceHelper {
   protected String attrPrefix;

   public ApkXmlResourceHelper(IXmlUnit var1) {
      super(var1);
   }

   public ApkXmlResourceHelper(Document var1) {
      super(var1);
   }

   @Override
   protected void prepare(Document var1) {
      super.prepare(var1);
      String var2 = null;
      NamedNodeMap var3 = this.rootElt.getAttributes();

      for (int var4 = 0; var4 < var3.getLength(); var4++) {
         Node var5 = var3.item(var4);
         if (var5.getTextContent().equals("http://schemas.android.com/apk/res/android")) {
            var2 = var5.getNodeName();
            break;
         }
      }

      if (var2 != null) {
         int var6 = var2.indexOf(58);
         if (var6 >= 0) {
            this.attrPrefix = var2.substring(var6 + 1);
         }
      }
   }

   @Override
   protected String buildAttributeName(String var1) {
      return this.attrPrefix != null && !this.isFullyQualifiedName(var1) ? this.attrPrefix + ":" + var1 : var1;
   }
}
