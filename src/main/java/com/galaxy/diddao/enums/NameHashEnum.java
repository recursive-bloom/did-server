package com.galaxy.diddao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author Ant
 * @Date 2022/11/3
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum  NameHashEnum {

    ROOT("","0x0000000000000000000000000000000000000000000000000000000000000000"),

    ETH("eth","0x93cdeb708b7545dc668eb9280176169d1c33cfd8ed6f04690a0bcc88a93fc4ae"),

    DID("did","0x8a74fc6994ef0554dd9cc95c3391f9cd66152031a0c1feacb835e3890805af5f"),

    DAO("dao","0xb5f2bbf81da581299d4ff7af60560c0ac854196f5227328d2d0c2bb0df33e553"),

    NFT("nft","0xb75cf4f3d8bc3deb317ed5216d898899d5cc6a783f65f6768eb9bcb89428670d"),

    FREE("free","0x5e8583a43e6021bb6c77d2b9d3b3062ff9495d42c4bca82d6e494d591f6e7492"),

    BOOL("bool","0x40ac243157e8ee9e1d3ebb43a6efa84aa344e17e5705664d894a8077933e41cf"),

    META("meta","0x033549da90d902eebcededec7286e6a5f4e7b23484d4b06c20bd6ed60e05d4ef"),

    TEST("test","0x04f740db81dc36c853ab4205bddd785f46e79ccedca351fc6dfcbd8cc9a33dd6")

    ;

  private String name;

  private String hash;

    /**
     * 根据hash获取名称
     * @param hash
     * @return
     */
  public static String getNameByHash(String hash){
      if(StringUtils.isBlank(hash)){
          return null;
      }

      for (NameHashEnum nameHashEnum : values()) {
          if(hash.equalsIgnoreCase(nameHashEnum.getHash())){
              return nameHashEnum.getName();
          }
      }
      return null;
  }


}
