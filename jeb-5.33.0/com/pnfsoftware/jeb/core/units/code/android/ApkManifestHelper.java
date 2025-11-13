package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.IXmlUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.BooleanUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ApkManifestHelper extends ApkXmlResourceHelper {
   Element appElt;

   public static boolean canParse(IApkUnit var0) {
      return UnitUtil.findChild(var0, "Manifest", IXmlUnit.class, false, 0) != null;
   }

   public ApkManifestHelper(IApkUnit var1) {
      super((IXmlUnit)UnitUtil.findChild(var1, "Manifest", IXmlUnit.class, false, 0));
   }

   public ApkManifestHelper(IXmlUnit var1) {
      super(var1);
   }

   public ApkManifestHelper(Document var1) {
      super(var1);
   }

   @Override
   protected void prepare(Document var1) {
      super.prepare(var1);
      if (!"manifest".equals(this.rootElt.getTagName())) {
         throw new IllegalArgumentException("Illegal Android manifest: no 'manifest' tag");
      } else {
         NodeList var2 = var1.getElementsByTagName("manifest");
         if (var2.getLength() == 0) {
            throw new JebRuntimeException("Illegal Android manifest: no 'manifest' tag");
         } else {
            var2 = this.rootElt.getElementsByTagName("application");
            if (var2.getLength() != 0) {
               this.appElt = (Element)var2.item(0);
            }
         }
      }
   }

   public Element getXmlManifestElement() {
      return this.rootElt;
   }

   private String buildClassName(String var1) {
      if (var1.startsWith(".")) {
         var1 = this.getPackageName() + var1;
      }

      return var1;
   }

   public String getPackageName() {
      Node var1 = this.rootElt.getAttributes().getNamedItem("package");
      return var1 == null ? null : var1.getTextContent();
   }

   public List getPermissions() {
      ArrayList var1 = new ArrayList();
      NodeList var2 = this.rootElt.getChildNodes();

      for (int var3 = 0; var3 < var2.getLength(); var3++) {
         Node var4 = var2.item(var3);
         if (checkNonNullNode(var4, var3, this.rootElt) && var4.getNodeType() == 1 && "uses-permission".equals(((Element)var4).getTagName())) {
            String var5 = ((Element)var4).getAttribute(this.buildAttributeName("name"));
            var1.add(var5);
         }
      }

      return var1;
   }

   public boolean hasApplication() {
      return this.appElt != null;
   }

   public String getApplicationName() {
      if (this.appElt == null) {
         return null;
      } else {
         Attr var1 = this.appElt.getAttributeNode(this.buildAttributeName("name"));
         if (var1 == null) {
            return null;
         } else {
            String var2 = var1.getNodeValue();
            var2 = this.buildClassName(var2);
            if (!var2.contains(".")) {
               var2 = this.getPackageName() + "." + var2;
            }

            return var2;
         }
      }
   }

   public String getApplicationComponentFactory() {
      if (this.appElt == null) {
         return null;
      } else {
         Attr var1 = this.appElt.getAttributeNode(this.buildAttributeName("appComponentFactory"));
         if (var1 == null) {
            return null;
         } else {
            String var2 = var1.getNodeValue();
            var2 = this.buildClassName(var2);
            if (!var2.contains(".")) {
               var2 = this.getPackageName() + "." + var2;
            }

            return var2;
         }
      }
   }

   public boolean isDebuggable() {
      if (this.appElt == null) {
         return false;
      } else {
         Attr var1 = this.appElt.getAttributeNode(this.buildAttributeName("debuggable"));
         return var1 != null ? BooleanUtils.toBoolean(var1.getNodeValue()) : false;
      }
   }

   public ApkManifestHelper.ApplicationDescription getApplicationDescription() {
      if (this.appElt == null) {
         return null;
      } else {
         ApkManifestHelper.ApplicationDescription var1 = new ApkManifestHelper.ApplicationDescription();
         var1.name = this.getApplicationName();
         var1.debuggable = this.isDebuggable();
         this.fillMetaData(this.appElt, var1.metadata);
         return var1;
      }
   }

   private void fillMetaData(Element var1, Map var2) {
      NodeList var3 = var1.getChildNodes();

      for (int var4 = 0; var4 < var3.getLength(); var4++) {
         Node var5 = var3.item(var4);
         if (checkNonNullNode(var5, var4, var1) && var5.getNodeType() == 1 && "meta-data".equals(((Element)var5).getTagName())) {
            Element var6 = (Element)var5;
            String var7 = var6.getAttribute(this.buildAttributeName("name"));
            if (!"".equals(var7)) {
               String var8 = var6.getAttribute(this.buildAttributeName("value"));
               var2.put(var7, var8);
            }
         }
      }
   }

   public ApkManifestHelper.AndroidSystemType getIntendedSystemType() {
      for (String var2 : this.readAttributes("uses-feature", "name")) {
         switch (var2) {
            case "android.hardware.type.watch":
               return ApkManifestHelper.AndroidSystemType.WEAR_OS;
            case "android.hardware.type.automotive":
               return ApkManifestHelper.AndroidSystemType.AUTOMOTIVE_OS;
         }
      }

      if (this.hasAttributeValue("meta-data", "name", "com.google.android.gms.car.application")) {
         return ApkManifestHelper.AndroidSystemType.ANDROID_AUTO;
      } else {
         return this.hasAttributeValue("category", "name", "android.intent.category.LEANBACK_LAUNCHER")
            ? ApkManifestHelper.AndroidSystemType.ANDROID_TV
            : ApkManifestHelper.AndroidSystemType.ANDROID;
      }
   }

   public List getActivities() {
      return this.getEndPointNames("activity");
   }

   public List getServices() {
      return this.getEndPointNames("service");
   }

   public List getReceivers() {
      return this.getEndPointNames("receiver");
   }

   public List getProviders() {
      return this.getEndPointNames("provider");
   }

   private List getEndPointNames(String var1) {
      ArrayList var2 = new ArrayList();

      for (Element var4 : this.getEndPointNodes(var1)) {
         String var5 = var4.getAttribute(this.buildAttributeName("name"));
         var2.add(this.buildClassName(var5));
      }

      return var2;
   }

   private List getEndPointNodes(String var1) {
      if (this.appElt == null) {
         return Collections.emptyList();
      } else {
         ArrayList var2 = new ArrayList();
         NodeList var3 = this.appElt.getChildNodes();

         for (int var4 = 0; var4 < var3.getLength(); var4++) {
            Node var5 = var3.item(var4);
            if (checkNonNullNode(var5, var4, this.appElt) && var5.getNodeType() == 1 && var1.equals(((Element)var5).getTagName())) {
               var2.add((Element)var5);
            }
         }

         return var2;
      }
   }

   public String getMainActivity() {
      for (Element var2 : this.getEndPointNodes("activity")) {
         Element var3 = this.getFirstChildElement(var2, "intent-filter");
         if (var3 != null) {
            Element var4 = this.getFirstChildElement(var3, "action");
            if (var4 != null) {
               String var5 = var4.getAttribute(this.buildAttributeName("name"));
               if ("android.intent.action.MAIN".equals(var5)) {
                  String var6 = var2.getAttribute(this.buildAttributeName("name"));
                  return this.buildClassName(var6);
               }
            }
         }
      }

      for (Element var8 : this.getEndPointNodes("activity-alias")) {
         Element var9 = this.getFirstChildElement(var8, "intent-filter");
         if (var9 != null) {
            Element var10 = this.getFirstChildElement(var9, "action");
            if (var10 != null) {
               String var11 = var10.getAttribute(this.buildAttributeName("name"));
               if ("android.intent.action.MAIN".equals(var11)) {
                  String var12 = var8.getAttribute(this.buildAttributeName("targetActivity"));
                  return this.buildClassName(var12);
               }
            }
         }
      }

      return null;
   }

   private Element getFirstChildElement(Element var1, String var2) {
      NodeList var3 = var1.getChildNodes();

      for (int var4 = 0; var4 < var3.getLength(); var4++) {
         Node var5 = var3.item(var4);
         if (checkNonNullNode(var5, var4, var1) && var5.getNodeType() == 1 && var2.equals(((Element)var5).getTagName())) {
            return (Element)var5;
         }
      }

      return null;
   }

   public List getEndPointDescriptions() {
      ArrayList var1 = new ArrayList();
      this.getEndPointDescriptions(ApkManifestHelper.EndPointType.ACTIVITY, var1);
      this.getEndPointDescriptions(ApkManifestHelper.EndPointType.SERVICE, var1);
      this.getEndPointDescriptions(ApkManifestHelper.EndPointType.RECEIVER, var1);
      this.getEndPointDescriptions(ApkManifestHelper.EndPointType.PROVIDER, var1);
      return var1;
   }

   public List getActivityDescriptions() {
      ArrayList var1 = new ArrayList();
      this.getEndPointDescriptions(ApkManifestHelper.EndPointType.ACTIVITY, var1);
      return var1;
   }

   public List getServiceDescriptions() {
      ArrayList var1 = new ArrayList();
      this.getEndPointDescriptions(ApkManifestHelper.EndPointType.SERVICE, var1);
      return var1;
   }

   public List getReceiverDescriptions() {
      ArrayList var1 = new ArrayList();
      this.getEndPointDescriptions(ApkManifestHelper.EndPointType.RECEIVER, var1);
      return var1;
   }

   public List getProviderDescriptions() {
      ArrayList var1 = new ArrayList();
      this.getEndPointDescriptions(ApkManifestHelper.EndPointType.PROVIDER, var1);
      return var1;
   }

   private void getEndPointDescriptions(ApkManifestHelper.EndPointType var1, List var2) {
      ArrayList var3 = new ArrayList(Arrays.asList(var1.toString().toLowerCase()));
      if (var1 == ApkManifestHelper.EndPointType.ACTIVITY) {
         var3.add("activity-alias");
      }

      for (String var5 : var3) {
         for (Element var7 : this.getEndPointNodes(var5)) {
            String var8 = var7.getAttribute(this.buildAttributeName("enabled"));
            String var9 = var7.getAttribute(this.buildAttributeName("exported"));
            String var10 = var7.getAttribute(this.buildAttributeName("name"));
            String var11 = this.buildClassName(var10);
            Element var12 = this.getFirstChildElement(var7, "intent-filter");
            boolean var13 = var12 != null;
            boolean var14;
            if (!var8.isEmpty()) {
               var14 = var8.equals("true");
            } else {
               var14 = true;
            }

            boolean var15;
            if (!var9.isEmpty()) {
               var15 = var9.equals("true");
            } else {
               var15 = var13;
            }

            String var16 = null;
            if (var5.equals("activity-alias")) {
               var16 = var7.getAttribute(this.buildAttributeName("targetActivity"));
               if (var16.isEmpty()) {
                  var16 = null;
               } else {
                  var16 = this.buildClassName(var16);
               }
            }

            ApkManifestHelper.IntentFilterDescriptions var17 = null;
            if (!var5.equals("provider")) {
               var17 = this.getIntentFilterDescriptions(var7);
            }

            switch (var1) {
               case ACTIVITY:
                  ApkManifestHelper.ActivityDescription var22 = new ApkManifestHelper.ActivityDescription(var11, var14, var15);
                  var22.aliasForName = var16;
                  var22.intentFilters = var17;
                  this.fillMetaData(var7, var22.metadata);
                  var2.add(var22);
                  break;
               case SERVICE:
                  ApkManifestHelper.ServiceDescription var21 = new ApkManifestHelper.ServiceDescription(var11, var14, var15);
                  var21.intentFilters = var17;
                  this.fillMetaData(var7, var21.metadata);
                  var2.add(var21);
                  break;
               case RECEIVER:
                  ApkManifestHelper.ReceiverDescription var20 = new ApkManifestHelper.ReceiverDescription(var11, var14, var15);
                  var20.intentFilters = var17;
                  this.fillMetaData(var7, var20.metadata);
                  var2.add(var20);
                  break;
               case PROVIDER:
                  ApkManifestHelper.ProviderDescription var18 = new ApkManifestHelper.ProviderDescription(var11, var14, var15);
                  this.fillMetaData(var7, var18.metadata);
                  var2.add(var18);
                  break;
               default:
                  throw new RuntimeException();
            }
         }
      }
   }

   private ApkManifestHelper.IntentFilterDescriptions getIntentFilterDescriptions(Element var1) {
      ApkManifestHelper.IntentFilterDescriptions var2 = new ApkManifestHelper.IntentFilterDescriptions();
      NodeList var3 = var1.getChildNodes();

      for (int var4 = 0; var4 < var3.getLength(); var4++) {
         Node var5 = var3.item(var4);
         if (checkNonNullNode(var5, var4, var1) && var5.getNodeType() == 1 && "intent-filter".equals(((Element)var5).getTagName())) {
            Element var6 = (Element)var5;
            ApkManifestHelper.IntentFilterDescription var7 = new ApkManifestHelper.IntentFilterDescription();
            NodeList var8 = var6.getChildNodes();

            for (int var9 = 0; var9 < var8.getLength(); var9++) {
               Node var10 = var8.item(var9);
               if (checkNonNullNode(var10, var9, var6)) {
                  if (var10.getNodeType() == 1 && "action".equals(((Element)var10).getTagName())) {
                     String var12 = ((Element)var10).getAttribute(this.buildAttributeName("name"));
                     var7.actions.add(var12);
                  } else if (var10.getNodeType() == 1 && "category".equals(((Element)var10).getTagName())) {
                     String var11 = ((Element)var10).getAttribute(this.buildAttributeName("name"));
                     var7.categories.add(var11);
                  }
               }
            }

            var2.list.add(var7);
         }
      }

      return var2;
   }

   public int[] getSdkVersion() {
      Element var1 = this.getFirstChildElement(this.rootElt, "uses-sdk");
      if (var1 == null) {
         return new int[]{1, 1};
      } else {
         String var2 = var1.getAttribute(this.buildAttributeName("minSdkVersion"));
         int var3 = Conversion.stringToInt(var2, 1);
         var2 = var1.getAttribute(this.buildAttributeName("targetSdkVersion"));
         int var4 = Conversion.stringToInt(var2, var3);
         return new int[]{var3, var4};
      }
   }

   private static boolean checkNonNullNode(Node var0, int var1, Node var2) {
      if (var0 != null) {
         return true;
      } else {
         RuntimeException var3 = new RuntimeException(Strings.ff("Null XML child %d for parent %s", var1, var2));
         JebCoreService.notifySilentExceptionToClient(var3);
         return false;
      }
   }

   public static class ActivityDescription extends ApkManifestHelper.EndPointDescription {
      String aliasForName;
      ApkManifestHelper.IntentFilterDescriptions intentFilters;

      public ActivityDescription(String var1, boolean var2, boolean var3) {
         super(var1, var2, var3);
      }

      public boolean isAlias() {
         return this.aliasForName != null;
      }

      public String getAliasForName() {
         return this.aliasForName;
      }

      public ApkManifestHelper.IntentFilterDescriptions getIntentFilters() {
         return this.intentFilters;
      }

      @Override
      public String toString() {
         String var1 = super.toString();
         if (this.isAlias()) {
            var1 = var1 + "->" + this.getAliasForName();
         }

         return var1;
      }
   }

   public static enum AndroidSystemType {
      ANDROID("Android"),
      WEAR_OS("Wear OS"),
      AUTOMOTIVE_OS("Android Automotive OS"),
      ANDROID_AUTO("Android Auto"),
      ANDROID_TV("Android TV");

      private final String name;

      private AndroidSystemType(String var3) {
         this.name = var3;
      }

      @Override
      public String toString() {
         return this.name;
      }
   }

   public static class ApplicationDescription {
      String name;
      boolean debuggable;
      Map metadata = new HashMap();

      public String getName() {
         return this.name;
      }

      public boolean isDebuggable() {
         return this.debuggable;
      }

      public Map getMetadata() {
         return Collections.unmodifiableMap(this.metadata);
      }
   }

   public static class EndPointDescription {
      String fqname;
      boolean enabled;
      boolean exported;
      Map metadata = new HashMap();

      public EndPointDescription(String var1, boolean var2, boolean var3) {
         this.fqname = var1;
         this.enabled = var2;
         this.exported = var3;
      }

      public String getFullyQualifiedName() {
         return this.fqname;
      }

      public boolean isEnabled() {
         return this.enabled;
      }

      public boolean isExported() {
         return this.exported;
      }

      public Map getMetadata() {
         return Collections.unmodifiableMap(this.metadata);
      }

      @Override
      public String toString() {
         return Strings.ff("%s,enabled:%s,exported:%s", this.fqname, this.enabled, this.exported);
      }
   }

   public static enum EndPointType {
      ACTIVITY,
      SERVICE,
      RECEIVER,
      PROVIDER;
   }

   public static class IntentFilterDescription {
      List actions = new ArrayList();
      List categories = new ArrayList();

      public List getActions() {
         return this.actions;
      }

      public List getCategories() {
         return this.categories;
      }

      public boolean check(String var1, String var2) {
         return (var1 == null || this.actions.contains(var1)) && (var2 == null || var2.contains(var2));
      }

      @Override
      public String toString() {
         return Strings.ff("actions:%s,categories:%s", this.actions, this.categories);
      }
   }

   public static class IntentFilterDescriptions {
      List list = new ArrayList();

      public boolean check(String var1, String var2) {
         for (ApkManifestHelper.IntentFilterDescription var4 : this.list) {
            if ((var1 == null || var4.actions.contains(var1)) && (var2 == null || var4.categories.contains(var2))) {
               return true;
            }
         }

         return false;
      }

      @Override
      public String toString() {
         return this.list.toString();
      }
   }

   public static class ProviderDescription extends ApkManifestHelper.EndPointDescription {
      public ProviderDescription(String var1, boolean var2, boolean var3) {
         super(var1, var2, var3);
      }
   }

   public static class ReceiverDescription extends ApkManifestHelper.EndPointDescription {
      ApkManifestHelper.IntentFilterDescriptions intentFilters;

      public ReceiverDescription(String var1, boolean var2, boolean var3) {
         super(var1, var2, var3);
      }

      public ApkManifestHelper.IntentFilterDescriptions getIntentFilters() {
         return this.intentFilters;
      }
   }

   public static class ServiceDescription extends ApkManifestHelper.EndPointDescription {
      ApkManifestHelper.IntentFilterDescriptions intentFilters;

      public ServiceDescription(String var1, boolean var2, boolean var3) {
         super(var1, var2, var3);
      }

      public ApkManifestHelper.IntentFilterDescriptions getIntentFilters() {
         return this.intentFilters;
      }
   }
}
