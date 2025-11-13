package com.pnfsoftware.jeb.corei.parsers.json;

import com.pnfsoftware.jeb.util.encoding.json.parser.ContainerFactory;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class ej implements ContainerFactory {
   ej(nI var1) {
      this.q = var1;
   }

   @Override
   public Map createObjectContainer() {
      return new LinkedHashMap();
   }

   @Override
   public List creatArrayContainer() {
      return new ArrayList();
   }
}
