    package com.wetrig.dev.wetrig.Retrofit;


    import com.wetrig.dev.wetrig.POJO.AddRatting;
    import com.wetrig.dev.wetrig.POJO.AddSystem;
    import com.wetrig.dev.wetrig.POJO.After;
    import com.wetrig.dev.wetrig.POJO.CreateFolderGateway;
    import com.wetrig.dev.wetrig.POJO.CreateFolderSystem;
    import com.wetrig.dev.wetrig.POJO.DeleteSystem;
    import com.wetrig.dev.wetrig.POJO.GetSystemId;
    import com.wetrig.dev.wetrig.POJO.Login;
    import com.wetrig.dev.wetrig.POJO.Profile;
    import com.wetrig.dev.wetrig.POJO.ProgramRun;
    import com.wetrig.dev.wetrig.POJO.RattingList;
    import com.wetrig.dev.wetrig.POJO.Register;
    import com.wetrig.dev.wetrig.POJO.RunStopSchedule;
    import com.wetrig.dev.wetrig.POJO.SwitchOnOff;
    import com.wetrig.dev.wetrig.POJO.NextToday;
    import com.wetrig.dev.wetrig.POJO.Tomorrow;
    import com.wetrig.dev.wetrig.POJO.Total_Devices;
    import com.wetrig.dev.wetrig.POJO.UpdateSystem;
    import com.wetrig.dev.wetrig.POJO.UserData;

    import retrofit2.Call;
    import retrofit2.http.Field;
    import retrofit2.http.FormUrlEncoded;
    import retrofit2.http.GET;
    import retrofit2.http.Headers;
    import retrofit2.http.POST;
    import retrofit2.http.Query;

    public interface RestInterface {




        //GET USER DATA

        @Headers({
                "Accept-Language: *",
                "User-Agent: Wetrig"
        })
        @GET("web_services/get_profile")
        Call<UserData> getUserData(@Query("email") String email);


        //UPDATE USER DATA

        @Headers({
                "Accept-Language: *",
                "User-Agent: Wetrig"
        })
        @FormUrlEncoded
        @POST("web_services/android_update_profile")
        Call<Profile> getProfileUpdater(@Field("profile_img") String image, @Field("gender") String gender,
                                        @Field("first_name") String first_name, @Field("last_name") String last_name,
                                        @Field("email") String email, @Field("birth_date") String birth_date, @Field("bio") String bio);



        //GET RATING

        @FormUrlEncoded
        @POST("web_services/ratting_listing")
        Call<RattingList> getRattingList(@Field("email") String email, @Field("system_type") String sys_type_id);


        //ADD RATING
        @Headers({
                "Accept-Language: *",
                "User-Agent: Wetrig"
        })
        @FormUrlEncoded
        @POST("web_services/add_ratting")
        Call<AddRatting> AddRatting(@Field("email") String email, @Field("star_rating") String star_rating,
                                    @Field("comment") String comment, @Field("system_type") String system_type);


        //ADD SYSTEMS

        @FormUrlEncoded
        @POST("web_services/add_system")
        Call<AddSystem> addSystem(@Field("user_email") String email, @Field("sys_name") String name,
                                  @Field("sys_type_id") String type,@Field("sys_public") String spublic, @Field("sys_desc") String desc,
                                  @Field("search_sys_location") String address, @Field("s_latitude") String lat,
                                  @Field("s_longitude") String lon);


        //UPDATE SYSTEMS

        @FormUrlEncoded
        @POST("web_services/update_system")
        Call<UpdateSystem> updateSystem( @Field("sys_name") String name,@Field("sys_public") String spublic,
                                      @Field("sys_desc") String desc, @Field("search_sys_location") String address,
                                        @Field("s_latitude") String lat, @Field("s_longitude") String lon,@Field("sys_type_id") String type, @Field("sys_id") String id,@Field("user_email") String email);




        //DELETE SYSTEM

        @FormUrlEncoded
        @POST("web_services/delete_system")
        Call<DeleteSystem> deleteSystem(@Field("email") String email, @Field("system_id") String id);


        //CREATE SYSTEMS FOLDER
        @Headers({
                "Accept-Language: *",
                "User-Agent: Wetrig"
        })
        @FormUrlEncoded
        @POST("web_services/create_folder_system")
        Call<CreateFolderSystem> createSystemFolder(@Field("email") String email, @Field("folder_name") String folder_name);








        //CREATE GATEWAY FOLDER
        @Headers({
                "Accept-Language: *",
                "User-Agent: Wetrig"
        })
        @FormUrlEncoded
        @POST("web_services/create_folder_gateway")
        Call<CreateFolderGateway> createFolderGateway(@Field("email") String email, @Field("folder_name") String folder_name);





        //GET TOTAL DEVICES AND STATUS

        @Headers({
                "Accept-Language: *",
                "User-Agent: Wetrig"
        })
        @GET("web_services/get_android_all_system_device")
        Call<Total_Devices> getTotalDevices(@Query("s_id") String id);

        //LOGIN
        @Headers({
                "Accept-Language: *",
                "User-Agent: Wetrig"
        })
        @FormUrlEncoded
        @POST("web_services/android_login_process")
        Call<Login> UserLogin(@Field("user_email") String email, @Field("password") String password);


        //REGISTER
        @Headers({
                "Accept-Language: *",
                "User-Agent: Wetrig"
        })

        @FormUrlEncoded
        @POST("web_services/android_user_registration")
        Call<Register> UserRegister(@Field("rmail") String email, @Field("pass") String password,@Field("reference") String reference, @Field("other_ref") String other_ref, @Field("reason") String reason, @Field("other_reason") String other_reason);


        //GET SYSTEM ID

        @Headers({
                "Accept-Language: *",
                "User-Agent: Wetrig"
        })


        @GET("web_services/android_get_systems")
        Call<GetSystemId> GetEmail(@Query("email") String systemId);


        //SET DEVICE ON/OFF
        @Headers({
                "Accept-Language: *",
                "User-Agent: Wetrig"
        })

        @FormUrlEncoded
        @POST("web_services/set_aqua_program_android")
        Call<SwitchOnOff> SwitchCommand(@Field("controllerId") String controllerId, @Field("pgmNum") String pgm,
                                        @Field("doProgram") String program, @Field("allowRun") String run,
                                        @Field("runNow") String runNow, @Field("terminal") String terminal, @Field("minutes") String minutes  );

//PROGRAMS RUN/STOP STATUS
        @FormUrlEncoded
        @POST("web_services/get_all_programs_status")
        Call<ProgramRun> programRunStop(@Field("email") String email, @Field("s_id") String id);




        //PROGRAMS RUN/STOP

        @FormUrlEncoded
        @POST("web_services/set_all_sys_schedule")
        Call<RunStopSchedule> runStopSchedule(@Field("s_id") String id, @Field("type") String command);


        // List Today Schedule programs

        @FormUrlEncoded
        @POST("web_services/next_get_all_today_schedule_name")
        Call<NextToday> todaySchedule(@Field("email") String email, @Field("s_id") String id);

        // List Tomorrow Schedule programs

        @FormUrlEncoded
        @POST("web_services/next_get_all_tommorrow_schedule_name")
        Call<Tomorrow> tomorrowSchedule(@Field("email") String email, @Field("s_id") String id);

        // List After Schedule programs

        @FormUrlEncoded
        @POST("web_services/next_get_all_after_schedule_name")

        Call<After> afterSchedule(@Field("email") String email, @Field("s_id") String id);




    }
