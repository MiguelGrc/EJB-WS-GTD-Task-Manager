using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AlternativeSOAPClient
{
    class Program
    {
        static void Main(string[] args)
        {
            int option;
            PrintSetOptions();
            while ((option = Convert.ToInt32(Console.ReadLine())) > 0)
            {
                ExecuteOption(option);
                PrintSetOptions();

            }
        }

        private static void ExecuteOption(int option)
        {
            if(option <= 3)
            {
                switch (option)
                {
                    case 1:
                        DeshabilitarUsuario();
                        break;
                    case 2:
                        EliminarUsuario();
                        break;
                    case 3:
                        ListarUsuarios();
                        break;
                }
            }
            else {
                Console.WriteLine("Wrong option");
            }
           
        }

        private static void DeshabilitarUsuario()
        {
            Console.WriteLine("Escriba el id del usuario a deshabilitar:");
            long id = 0;
            long.TryParse(Console.ReadLine(), out id);
            EjbAdminServiceService service = new EjbAdminServiceService();
            service.disableUser(id, true);

            Console.WriteLine("La operación se ha finalizado satisfactoriamente.");
            
        }

        private static void EliminarUsuario()
        {
            Console.WriteLine("Escriba el id del usuario a eliminar:");
            long id = 0;
            long.TryParse(Console.ReadLine(), out id);
            EjbAdminServiceService service = new EjbAdminServiceService();
            service.deepDeleteUser(id, true);

            Console.WriteLine("La operación se ha finalizado satisfactoriamente.");

        }

        private static void ListarUsuarios()
        {

            EjbAdminServiceService service = new EjbAdminServiceService();
            var usersInfo = service.findAllUsersInfo();

            PrintHeader();

            foreach (userInfo userInf in usersInfo)
            {
                StringBuilder sb = new StringBuilder();

                sb.Append(userInf.user.id + " \t");
                sb.Append(userInf.user.login + "   \t");
                sb.Append(userInf.user.email + "  \t");
                sb.Append((userInf.user.isAdmin ? "yes" : "no") + "    \t \t");
                sb.Append((userInf.user.status == userStatus.ENABLED ? "ENABLED" : "DISABLED") + " \t");
                sb.Append(userInf.numTareasCompletadas + "   \t \t");
                sb.Append(userInf.numTareasCompletadasRetrasadas + "      \t \t \t");
                sb.Append(userInf.numTareasPlanificadas + "       \t \t");
                sb.Append(userInf.numTareasNoPlanificadas + "      \t \t");
                sb.Append("\n");

                Console.Write(sb.ToString());

            }

        }

        private static void PrintHeader()
        {

            Console.WriteLine("Listando a todos los usuarios del sistema...");

            StringBuilder sb = new StringBuilder();
            sb.Append("ID \t");
            sb.Append("Login   \t");
            sb.Append("Email            \t");
            sb.Append("IsAdmin  \t");
            sb.Append("Status  \t");
            sb.Append("numTarCompl \t");
            sb.Append("numTarComplRetrasadas \t");
            sb.Append("numTarPlanificadas \t");
            sb.Append("numTarNoPlanificadas \t");
            sb.Append("\n");

            Console.WriteLine(sb.ToString());

        }


        private static void PrintSetOptions()
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("Seleccione una de estas opciones:\n \n");
            sb.Append("\t DeshabilitarUsuario: 1\n");
            sb.Append("\t EliminarUsuario: 2\n");
            sb.Append("\t ListarUsuarios: 3\n");
            sb.Append("Tu opción:");
            Console.Write(sb.ToString());
        }
    }
}
