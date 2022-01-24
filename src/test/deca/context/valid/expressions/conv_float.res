`> [12, 0] Program
   +> [12, 0] ListDeclClass [List with 0 elements]
   `> [12, 0] Main
      +> [13, 4] ListDeclVar [List with 0 elements]
      `> [13, 4] ListInst [List with 2 elements]
         []> [13, 4] NotEquals
         ||  type: boolean
         ||  +> ConvFloat
         ||  |  type: float
         ||  |  `> [13, 4] Int (3)
         ||  |     type: int
         ||  `> [13, 9] Float (3.0)
         ||     type: float
         []> [14, 4] NotEquals
             type: boolean
             +> [14, 4] Float (3.0)
             |  type: float
             `> ConvFloat
                type: float
                `> [14, 11] Int (3)
                   type: int
