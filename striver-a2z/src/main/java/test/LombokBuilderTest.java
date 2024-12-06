package test;


import lombok.*;

@Builder
@Data

public class LombokBuilderTest {

   private String name;
   @Builder.Default
   private int id = 5;

//   public LombokBuilderTest(){
//       this.id=344;
//   }

    public static void main(String[] args) {
        LombokBuilderTest t = LombokBuilderTest.builder()
                .name("name")
                .build();

        System.out.println(t);

        LombokBuilderTest jj =  new LombokBuilderTest("yuy", 3);
        System.out.println(jj);


    }
}
